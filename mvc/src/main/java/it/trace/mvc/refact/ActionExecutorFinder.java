package it.trace.mvc.refact;

import it.trace.mvc.config.Configuration;

import java.util.HashMap;
import java.util.Map;


public class ActionExecutorFinder {
    protected Map<String, HttpExecutor> mapper = new HashMap<String, HttpExecutor>();
    protected Configuration configuration;

    public ActionExecutorFinder(Configuration configuration) {
        this.configuration = configuration;
    }

    public HttpExecutor find(String httpMethod, String path) {
        HttpExecutor ex = mapper.get(path);
        if (ex != null) {
            return ex;
        }

        String[] sa = path.split("/"); //
        if ("post".equals(httpMethod)) {
            ex = configuration.getNamespace(sa[0]).getActionConfig(sa[1] + "/list").getExecutor(null);
        } else if ("put".equals(httpMethod)) {
            ex = configuration.getNamespace(sa[0]).getActionConfig(sa[1] + "/edit").getExecutor(sa[2]);
        } else if("get".equals(httpMethod)) {
            String[] ssa = sa[2].split("!");
            if(ssa.length=1){
                ex = configuration.getNamespace(sa[0]).getActionConfig(sa[1] + ssa[1]).getExecutor(sa[2]);
            }
        }else{
            throw new RuntimeException();
        }

        //
        //        " *  <li><code>GET:    /movie/                       => method=""list""</code></li>
        //        *  <li><code>GET:    /movie/Thrillers              => method=""view"", id=""Thrillers""</code></li>
        //        *  <li><code>GET:    /movie/Thrillers!editable     => method=""edit"", id=""Thrillers""</code></li>
        //        *  <li><code>GET:    /movie/Thrillers!removable    => method=""edit"", id=""Thrillers""</code></li>
        //        *  <li><code>GET:    /movie/new                    => method=""editNew""</code></li>
        //        *  <li><code>POST:   /movie/                       => method=""create""</code></li>
        //        *  <li><code>PUT:    /movie/Thrillers              => method=""update"", id=""Thrillers""</code></li>
        //        *  <li><code>DELETE: /movie/Thrillers              => method=""remove"", id=""Thrillers""</code></li>"
        //
        //


        return null;
    }
}
