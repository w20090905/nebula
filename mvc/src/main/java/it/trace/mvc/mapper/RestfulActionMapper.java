package it.trace.mvc.mapper;

import it.trace.mvc.RequestUtil;
import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.NamespaceConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <ul>
 *  <li><code>GET:    /movie/               => method="list"</code></li>
 *  <li><code>GET:    /movie/Thrillers      => method="view", id="Thrillers"</code></li>
 *  <li><code>GET:    /movie/Thrillers!edit => method="edit", id="Thrillers"</code></li>
 *  <li><code>GET:    /movie/new            => method="editNew"</code></li>
 *  <li><code>POST:   /movie/               => method="create"</code></li>
 *  <li><code>PUT:    /movie/Thrillers      => method="update", id="Thrillers"</code></li>
 *  <li><code>DELETE: /movie/Thrillers      => method="remove", id="Thrillers"</code></li>
 * </ul>
 */
public class RestfulActionMapper implements ActionMapper {

    @Override
    public ActionMapping getActionMapping(HttpServletRequest request, HttpServletResponse response, Configuration config) {

        String uri = RequestUtil.getServletPath(request);   // This path must start with a "/" character.

        String namespace = "";
        String name = "";

        int lastSlash = uri.lastIndexOf('/');
        if (lastSlash > 0) {
            namespace = "";
            // TODO 优化
            for (NamespaceConfig cfg : config.getNamespaceConfigs().values()) {
                String ns = cfg.getName();
                if (ns != null && uri.startsWith(ns) && (uri.length() == ns.length() || uri.charAt(ns.length()) == '/')) {
                    if (ns.length() > namespace.length()) {
                        namespace = ns;
                    }
                }
            }
            name = uri.substring(namespace.length() + 1);
            if (name.endsWith("/")) {
                name = name.substring(0, name.length() -1);
            }
        } else if (lastSlash == 0) {
            namespace = "/";
            name = uri.substring(lastSlash + 1);;
        } else {
            // impossibility
        }

        String methodName = null;
        String id = null;

        if (name.length() > 0) {

            int lastSlashPos = name.lastIndexOf('/');
            if (lastSlashPos > -1) {
                id = name.substring(lastSlashPos + 1);
                name = name.substring(0, lastSlashPos);
            }

            if (id == null) {
                if (RequestUtil.isGet(request)) {
                    methodName = "list";
                } else if (RequestUtil.isPost(request)) {
                    methodName = "create";
                }
            } else {
                if (RequestUtil.isGet(request) && "new".equals(id)) {
                    if ("new".equals(id)) {
                        methodName = "editNew";
                    } else {
                        methodName = "view";
                    }
                } else if (RequestUtil.isDelete(request)) {
                    methodName = "remove";
                } else if (RequestUtil.isPut(request)) {
                    methodName = "update";
                }
            }

        } else {
            // no name, do nothing now.
        }

        NamespaceConfig nsc = config.getNamespaceConfig(namespace);
        if (nsc == null) {
            return null;
        }

        ActionConfig ac = nsc.getActionConfig(name + "/" + methodName);
        if (ac == null) {
            return null;
        }

        ActionMapping mapping = new ActionMapping();
        mapping.setActionConfig(ac);
        if (id != null) {
            mapping.addParamter("id", id);
        }

        return mapping;
    }

}
