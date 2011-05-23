package it.trace.mvc.executor;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.Resource;

import java.util.HashMap;
import java.util.Map;

public class SimpleRESTfulExecutorFinder {

    protected Map<String, ActionExecutor> cache = new HashMap<String, ActionExecutor>();
    protected Configuration configuration;

    public SimpleRESTfulExecutorFinder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * <ul>
     *  <li><code>/user/            => namespace=""</code></li>
     *  <li><code>/manage/user/     => namespace="manage"</code></li>
     *  <li><code>/manage/admin/user/     => namespace="manage.admin"</code></li>
     * </ul>
     * <ul>
     *  <li><code>GET:    /user/                  => method="list"</code></li>
     *  <li><code>GET:    /user/Tom/              => method="view", id="Tom"</code></li>
     *  <li><code>GET:    /user/Tom!editable/     => method="edit", id="Tom"</code></li>
     *  <li><code>GET:    /user/Tom!removable/    => method="edit", id="Tom"</code></li>
     *  <li><code>GET:    /user/new/              => method="editNew"</code></li>
     *  <li><code>POST:   /user/                  => method="create"</code></li>
     *  <li><code>PUT:    /user/Tom/              => method="update", id="Tom"</code></li>
     *  <li><code>DELETE: /user/Tom/              => method="remove", id="Tom"</code></li>
     * </ul>
     */
    public HttpExecutor find(HttpMethod httpMethod, String path) {
        assert path != null : "参数”path“不能为空。";
        assert path.startsWith("/") : "参数”path“必须以”/“开头。";


        ActionExecutor ex = cache.get(path);
        if (ex != null) {
            return ex;
        }

        String[] sa = path.substring(1).split("/");
        if (sa.length < 2) {
            return null;
        }

        String ns = sa[0];
        String name = sa[1];
        String id = null;
        String method = null;

        if (sa.length == 3) {
            id = sa[2];
            int exclamation = id.indexOf("!");
            if (exclamation > -1) {
                method = id.substring(exclamation + 1);
                id = id.substring(0, exclamation);
            }
        }

        if (HttpMethod.GET.equals(httpMethod)) {
            Resource r = configuration.getNamespace(ns).getResource(name);
            if (id == null) {
                r.getOperation("list").getExecutor();
            } else {

            }
        }
        else if (HttpMethod.POST.equals(httpMethod)) {
            if (sa.length == 2) {
                ex = configuration.getNamespace(sa[0]).getResource(sa[1]).getOperation("create").getExecutor();
            }
        } else if (HttpMethod.PUT.equals(httpMethod)) {
            if (sa.length == 3) {
                ex = configuration.getNamespace(sa[0]).getResource(sa[1]).getOperation("update").getExecutor();
                ex.setId(sa[2]);
            }
        } else if (HttpMethod.DELETE.equals(httpMethod)) {
            if (sa.length == 3) {
                ex = configuration.getNamespace(sa[0]).getResource(sa[1]).getOperation("remove").getExecutor();
                ex.setId(sa[2]);
            }
        }

        return ex;
    }
}
