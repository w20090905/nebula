package it.trace.mvc.mapper;

import it.trace.mvc.RequestUtil;
import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.NamespaceConfig;

import java.util.HashMap;

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

        ActionMapping mapping = new ActionMapping();

        mapping.setParams(new HashMap<String, Object>());

        String uri = RequestUtil.getServletPath(request);
        parseNameAndNamespace(uri, config, mapping);
        parseActionName(mapping);



        String actionName = mapping.getName();
        String id = null;

        // Only try something if the action name is specified
        if (actionName.length() > 0) {

            int lastSlashPos = actionName.lastIndexOf('/');
            if (lastSlashPos > -1) {
                id = actionName.substring(lastSlashPos+1);
                mapping.getParams().put("id", id);  // TODO
            }


            // If a method hasn't been explicitly named, try to guess using ReST-style patterns
            if (mapping.getMethod() == null) {

                if (lastSlashPos == actionName.length() -1) {

                    // Index e.g. foo/
                    if (isGet(request)) {
                        mapping.setMethod("list");

                        // Creating a new entry on POST e.g. foo/
                    } else if (isPost(request)) {
                        mapping.setMethod("create");
                    }

                } else if (lastSlashPos > -1) {
                    // Viewing the form to create a new item e.g. foo/new
                    if (isGet(request) && "new".equals(id)) {
                        mapping.setMethod("editNew");

                        // Viewing an item e.g. foo/1
                    } else if (isGet(request)) {
                        mapping.setMethod("view");

                        // Removing an item e.g. foo/1
                    } else if (isDelete(request)) {
                        mapping.setMethod("remove");

                        // Updating an item e.g. foo/1
                    }  else if (isPut(request)) {
                        mapping.setMethod("update");
                    }

                }
            }
        }



        NamespaceConfig nsc = config.getNamespaceConfig(mapping.getNamespace());
        if (nsc == null) {
            return null;
        }

        ActionConfig ac = nsc.getActionConfig(mapping.getName() + "." + mapping.getMethod());

        mapping.setActionConfig(ac);

        return mapping;
    }

    protected void parseNameAndNamespace(String uri, Configuration config, ActionMapping mapping) {
        String namespace, name;
        int lastSlash = uri.lastIndexOf("/");
        if (lastSlash == -1) {
            namespace = "";
            name = uri;
        } else if (lastSlash == 0) {
            // ww-1046, assume it is the root namespace, it will fallback to
            // default
            // namespace anyway if not found in root namespace.
            namespace = "";
            name = uri.substring(lastSlash + 1);
        } else {
            // Try to find the namespace in those defined, defaulting to ""
            String prefix = uri.substring(0, lastSlash);
            namespace = "";
            boolean rootAvailable = false;
            // Find the longest matching namespace, defaulting to the default
            for (NamespaceConfig cfg : config.getNamespaceConfigs().values()) {
                String ns = cfg.getName();
                if (ns != null && prefix.startsWith(ns) && (prefix.length() == ns.length() || prefix.charAt(ns.length()) == '/')) {
                    if (ns.length() > namespace.length()) {
                        namespace = ns;
                    }
                }
                if ("/".equals(ns)) {
                    rootAvailable = true;
                }
            }

            name = uri.substring(namespace.length() + 1);

            // Still none found, use root namespace if found
            if (rootAvailable && "".equals(namespace)) {
                namespace = "/";
            }
        }

        mapping.setNamespace(namespace);
        mapping.setName(name);
    }

    protected ActionMapping parseActionName(ActionMapping mapping) {
        String name = mapping.getName();
        int exclamation = name.lastIndexOf("!");
        if (exclamation != -1) {
            mapping.setName(name.substring(0, exclamation));
            mapping.setMethod(name.substring(exclamation + 1));
        }
        return mapping;
    }

    protected boolean isGet(HttpServletRequest request) {
        return "get".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isPost(HttpServletRequest request) {
        return "post".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isPut(HttpServletRequest request) {
        return "put".equalsIgnoreCase(request.getMethod());
    }

    protected boolean isDelete(HttpServletRequest request) {
        return "delete".equalsIgnoreCase(request.getMethod());
    }

}
