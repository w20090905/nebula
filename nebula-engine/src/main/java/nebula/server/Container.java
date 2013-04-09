package nebula.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Container {

	void handle(HttpServletRequest req, HttpServletResponse resp);

}
