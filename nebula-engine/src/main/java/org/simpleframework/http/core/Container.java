package org.simpleframework.http.core;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public interface Container {

	void handle(Request req, Response resp);

}
