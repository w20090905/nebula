package http.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nebula.data.Entity;
import nebula.server.Resource;

public class RedirectCurrentUserResouce implements Resource {
	public RedirectCurrentUserResouce() {
	}

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Entity user = (Entity) session.getAttribute("#currentUser");

		Cookie loginUserID = new Cookie("LoginUserID", (String)user.getID());
		loginUserID.setPath("/");
		resp.addCookie(loginUserID);

		String redirectTo = "/u/" + user.getID();
		// normal parse
		resp.setStatus(302);
		resp.addHeader("location", redirectTo);
		resp.flushBuffer();
	}
}
