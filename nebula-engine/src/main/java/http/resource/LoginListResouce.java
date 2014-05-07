package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginListResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	private final DataStore<Entity> users;
	private final DataStore<Entity> userAccessLogs;
	final RedirectResouce redirectTo;
	private final String userKey;

	public LoginListResouce(DataStore<Entity> users, DataStore<Entity> userAccessLogs) {
		this.users = users;
		this.userAccessLogs = userAccessLogs;
		this.userKey = users.getType().getName() + users.getType().getKeyField().getName();
		redirectTo = new RedirectResouce("/index.html");
	}

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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

	protected void post(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String username = req.getParameter("username");
			// String password = req.getParameter("password");

			Entity user = users.get(username);
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

			// 用于在客户端显示用户登录ID
			Cookie cookieUserUD = new Cookie("LoginUserID", username);
			cookieUserUD.setPath("/");
			resp.addCookie(cookieUserUD);
			// normal parse
			resp.addHeader("Cache-Control", "max-age=0");
			resp.addHeader("Content-Language", "en-US");
			resp.addHeader("Content-Type", "text/html");
			resp.addIntHeader("Content-Length", 0);

			redirectTo.redirectTo(req, resp, "/u/" + req.getParameter("username"));

			// Log access info
			EditableEntity accessLog = new EditableEntity();
			accessLog.put(userKey, user.getID());
			accessLog.put("UserAction", "Login");
			accessLog.put("RemoteAddr", req.getRemoteAddr());
			accessLog.put("RemoteHost", req.getRemoteHost());
			accessLog.put("Timestamp", System.currentTimeMillis());
			userAccessLogs.add(accessLog);
			userAccessLogs.flush();

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
