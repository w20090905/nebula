package org.simpleframework.http.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Container {

	void handle(HttpServletRequest req, HttpServletResponse resp);

}
