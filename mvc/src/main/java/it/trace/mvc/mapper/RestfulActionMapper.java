package it.trace.mvc.mapper;

import it.trace.mvc.RequestUtils;
import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.NamespaceConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <ul>
 *  <li><code>GET:    /movie/                       => method="list"</code></li>
 *  <li><code>GET:    /movie/Thrillers              => method="view", id="Thrillers"</code></li>
 *  <li><code>GET:    /movie/Thrillers!editable     => method="edit", id="Thrillers"</code></li>
 *  <li><code>GET:    /movie/Thrillers!removable    => method="edit", id="Thrillers"</code></li>
 *  <li><code>GET:    /movie/new                    => method="editNew"</code></li>
 *  <li><code>POST:   /movie/                       => method="create"</code></li>
 *  <li><code>PUT:    /movie/Thrillers              => method="update", id="Thrillers"</code></li>
 *  <li><code>DELETE: /movie/Thrillers              => method="remove", id="Thrillers"</code></li>
 * </ul>
 */
public class RestfulActionMapper implements ActionMapper {

    public static final String HTTP_METHOD_PARAM = "__http_method";

    @Override
    public ActionMapping getActionMapping(HttpServletRequest request, HttpServletResponse response, Configuration config) {

        String uri = RequestUtils.getServletPath(request);   // This path must start with a "/" character.

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
            namespace = "";
            name = uri.substring(lastSlash + 1);;
        } else {
            // impossibility
        }

        String methodName = null;
        String id = null;

        if (name.length() > 0) {

            int exclamation = name.lastIndexOf("!");
            if (exclamation > -1) {
                methodName = name.substring(exclamation + 1);
                name = name.substring(0, exclamation);
            }

            int lastSlashPos = name.lastIndexOf('/');
            if (lastSlashPos > -1) {
                id = name.substring(lastSlashPos + 1);
                name = name.substring(0, lastSlashPos);
            }

            if (id == null) {
                if (isGet(request)) {
                    methodName = "list";
                } else if (isPost(request)) {
                    methodName = "create";
                }
            } else if (methodName == null) {
                if (isGet(request)) {
                    if ("new".equals(id)) {
                        methodName = "editNew";
                        id = null;
                    } else {
                        methodName = "view";
                    }
                } else if (isDelete(request)) {
                    methodName = "remove";
                } else if (isPut(request)) {
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

    public final static boolean isGet(HttpServletRequest request) {
        return "get".equalsIgnoreCase(request.getMethod());
    }

    public final static boolean isPost(HttpServletRequest request) {
        return "post".equalsIgnoreCase(request.getMethod());
    }

    public final static boolean isPut(HttpServletRequest request) {
        if ("put".equalsIgnoreCase(request.getMethod())) {
            return true;
        } else {
            return isPost(request) && "put".equalsIgnoreCase(request.getParameter(HTTP_METHOD_PARAM));
        }
    }

    public final static boolean isDelete(HttpServletRequest request) {
        if ("delete".equalsIgnoreCase(request.getMethod())) {
            return true;
        } else {
            return isPost(request) && "delete".equalsIgnoreCase(request.getParameter(HTTP_METHOD_PARAM));
        }
    }

}
