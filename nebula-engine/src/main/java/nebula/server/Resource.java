package nebula.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Resource {

	public void handle(Address target, HttpServletRequest req, HttpServletResponse resp) throws IOException,
			ServletException;
}
