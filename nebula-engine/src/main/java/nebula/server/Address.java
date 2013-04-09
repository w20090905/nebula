/*
 * Address.java February 2001
 *
 * Copyright (C) 2001, Niall Gallagher <niallg@users.sf.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package nebula.server;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Address implements Path {
	final HttpServletRequest req;
	String name = null;
	String path = null;
	String[] segments = null;
	String extension = null;

	public Address(HttpServletRequest req) {
		this.req = req;
	}

	public Path getPath() {
		String pathInfo = req.getPathInfo();
		segments = pathInfo.split("/");
		name = segments[segments.length - 1];

		return this;
	}

	public Map<String, String[]> getParameterMap() {
		return req.getParameterMap();
	}

	public boolean isQueryEmpty() {
		String q = req.getQueryString();
		return q == null || q.length() < 1;
	}

	@Override
	public String getExtension() {
		return name.substring(name.indexOf('.') + 1);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getPathInfo() {
		return req.getPathInfo();
	}

	@Override
	public String[] getSegments() {
		return this.segments;
	}

}
