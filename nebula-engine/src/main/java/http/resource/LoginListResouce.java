package http.resource;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Form;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;
import org.simpleframework.util.lease.LeaseException;

public class LoginListResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	private final DataHolder<DataStore<Entity>> users;
	final RedirectResouce redirectTo;

	public LoginListResouce(DataHelper<Entity,Reader,Writer> json, DataHolder<DataStore<Entity>> users, DataHolder<DataStore<Entity>> datas) {
		this.users = users;
		redirectTo = new RedirectResouce("/index.html");
	}

	@Override
	public void handle(Request req, Response resp) {
		if (log.isTraceEnabled()) {
			log.trace("Request : " + req.getPath());
			log.trace("\tMethod" + req.getMethod());
		}

		String method = req.getMethod();

		try {
			if ("POST".equals(method)) {
				this.post(req, resp);

				// normal parse
				resp.set("Cache-Control", "max-age=0");
				resp.set("Content-Language", "en-US");
				resp.set("Content-Type", "text/html");
				resp.set("Content-Length", 0);
				
				
				if(resp.getCode()==200){
					redirectTo.redirectTo(req, resp,"/index.html#/c/" +  ((Entity)req.getSession().get("#currentUser")).getID());					
				}
				
				resp.close();
			} else {
				throw new RuntimeException("Unsupport method " + method);
			}
		} catch (IOException | LeaseException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected void post(Request req, Response resp) {
		try {

			Form form = req.getForm();
			String username = form.get("username");
			// String password = form.get("password");
			Entity user = users.get().get(username);
			if (user == null) {
				resp.setCode(403);
				return;
			}

			resp.setCode(200);
			req.getSession().put("#currentUser", user);
			resp.setCookie("LoginUserID", username);
			
			//
			// Entity data = datas.createNew();
			// InputStream in = req.getInputStream();
			// if (log.isTraceEnabled()) {
			// in = FileUtil.print(in);
			// }
			// json.readFrom(data, in);
			//
			//
			// datas.add(data);
			//datas.flush();

		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (LeaseException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
