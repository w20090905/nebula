package it.trace.mvc.executor;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Namespace;
import it.trace.mvc.config.Operation;
import it.trace.mvc.config.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRESTfulExecutorFinder extends ExecutorFinder {

    protected static Pattern URI_PATTERN = Pattern.compile("/([^/]+)/([^/]+)/(?:([^/!]*)(?:!([^/]*)|)(?:/|)|)$");

    protected Map<String, ActionExecutor> cache = new HashMap<String, ActionExecutor>();

    /**
     * <ul>
     *  <li><code>/user/                  => namespace=""</code></li>
     *  <li><code>/manage/user/           => namespace="manage"</code></li>
     * </ul>
     * <ul>
     *  <li><code>GET:    /user/                  => method="list"</code></li>
     *  <li><code>GET:    /user/Tom/              => method="view", id="Tom"</code></li>
     *  <li><code>GET:    /user/Tom!editable/     => method="editable", id="Tom"</code></li>
     *  <li><code>GET:    /user/Tom!removable/    => method="removable", id="Tom"</code></li>
     *  <li><code>GET:    /user/new/              => method="editNew"</code></li>
     *  <li><code>POST:   /user/                  => method="create"</code></li>
     *  <li><code>PUT:    /user/Tom/              => method="update", id="Tom"</code></li>
     *  <li><code>DELETE: /user/Tom/              => method="remove", id="Tom"</code></li>
     * </ul>
     */
    public HttpExecutor find(HttpMethod httpMethod, String path) {
        assert path != null : "参数”path“不能为空。";
        assert path.startsWith("/") : "参数”path“必须以”/“开头。";

        String cacheKey = httpMethod + path;

        ActionExecutor ex = cache.get(cacheKey);
        if (ex != null) {
            return cache.get(cacheKey);
        }

        Matcher m = URI_PATTERN.matcher(path);
        if (!m.matches()) {
            return null;
        }

        String ns = m.group(1);
        String rs = m.group(2);
        String id = m.group(3);
        String me = m.group(4);

        Namespace n = config.getNamespace(ns);
        if (n == null) {
            return null;
        }

        Resource r = n.getResource(rs);
        if (r == null) {
            return null;
        }

        Operation o = null;
        if (me != null && !me.isEmpty()) {
            o = r.getOperation(me);
        } else if (HttpMethod.GET.equals(httpMethod)) {
            if (id == null || id.isEmpty()) {
                o = r.getOperation("list");
            } else {
                if ("new".equals(id)) {
                    o = r.getOperation("editNew");
                    id = null;
                } else {
                    o = r.getOperation("view");
                }
            }
        } else if (HttpMethod.POST.equals(httpMethod)) {
            o = r.getOperation("create");
        } else if (id != null && !id.isEmpty()) {
            if (HttpMethod.PUT.equals(httpMethod)) {
                o = r.getOperation("update");
            } else if (HttpMethod.DELETE.equals(httpMethod)) {
                o = r.getOperation("remove");
            }
        }

        if (o != null) {
            ex = o.getExecutor();
            cache.put(cacheKey, ex);
            if (id != null && !id.isEmpty()) {
                ex.setId(id);
            }
        }

        return ex;
    }

}
