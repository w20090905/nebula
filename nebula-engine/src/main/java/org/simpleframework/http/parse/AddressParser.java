/*
 * AddressParser.java February 2001
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

package org.simpleframework.http.parse;

import javax.servlet.http.HttpServletRequest;

import org.simpleframework.http.Address;
import org.simpleframework.http.Path;
import org.simpleframework.http.Query;

public class AddressParser extends Address {
	final HttpServletRequest r;
	public AddressParser(HttpServletRequest request){
		super(request);
		this.r = request;
	}
	

	@Override
	public String getDomain() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getPort() {
//		return r.getServerPort();

		return 0;
	}

	@Override
	public Path getPath() {
//		return new PathParser(r.getPathInfo());

		return null;
	}

	@Override
	public Query getQuery() {
		// TODO Auto-generated method stub
//		return new QueryParser(r.getQueryString());
		return null;
	}

}
