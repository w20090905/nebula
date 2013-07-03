package http.resource;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginListResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	private final Broker<DataStore<Entity>> users;
	final RedirectResouce redirectTo;

	public LoginListResouce(DataHelper<Entity, Reader, Writer> json, Broker<DataStore<Entity>> users,
			Broker<DataStore<Entity>> datas) {
		this.users = users;
		redirectTo = new RedirectResouce("/index.html");
	}

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException,
			ServletException {
		if (log.isTraceEnabled()) {
			log.trace("\tMethod" + req.getMethod());
		}

		String method = req.getMethod();

		if ("POST".equals(method)) {
			this.post(req, resp);
		} else {
			throw new RuntimeException("Unsupport method " + method);
		}
	}

	// @SuppressWarnings("unchecked")
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String username = req.getParameter("username");
			// String password = form.get("password");
			Entity user = users.get().get(username);
			if (user == null) {
				resp.setStatus(403);
				redirectTo.redirectTo(req, resp, "/login.html");
				return;
			}

			resp.setStatus(200);
			HttpSession session = req.getSession();
			
			session.setAttribute("#currentUser", user);
			session.setAttribute("Theme", "angularjs");
			session.setAttribute("Skin", "unicorn");
			 Cookie loginUserID = new Cookie("LoginUserID", username);
			 loginUserID.setPath("/");
			resp.addCookie(loginUserID);
			// normal parse
			resp.addHeader("Cache-Control", "max-age=0");
			resp.addHeader("Content-Language", "en-US");
			resp.addHeader("Content-Type", "text/html");
			resp.addIntHeader("Content-Length", 0);

			redirectTo.redirectTo(req, resp, "/u/" + req.getParameter("username"));

			resp.flushBuffer();

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
			// datas.flush();

		} finally {

		}
	}
}
