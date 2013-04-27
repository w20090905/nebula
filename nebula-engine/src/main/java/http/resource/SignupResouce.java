package http.resource;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.json.DataHelper;
import nebula.server.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SignupResouce implements Resource {
	protected Log log = LogFactory.getLog(this.getClass());

	private final DataHolder<DataStore<Entity>> users;
	final RedirectResouce redirectTo;

	public SignupResouce(DataHelper<Entity, Reader, Writer> json, DataHolder<DataStore<Entity>> users) {
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
			DataStore<Entity> store = users.get();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String passwordConfirm = req.getParameter("passwordConfirm");

			if (!password.equals(passwordConfirm)) {
				resp.setStatus(403);
				redirectTo.redirectTo(req, resp, "/signup.html");
				return;
			}
			
			
			EditableEntity newuser = new EditableEntity();
			newuser.put("Name", username);
			newuser.put("Password", password);
			newuser.put("EMail", email);
			store.add(newuser);
			store.flush();

			redirectTo.redirectTo(req, resp, "/login.html");

		} finally {

		}
	}
}
