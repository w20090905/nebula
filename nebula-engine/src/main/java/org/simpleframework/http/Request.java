/*
 * Request.java February 2001
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
 
package org.simpleframework.http;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.simpleframework.http.parse.AddressParser;


public class Request {
	HttpServletRequest request;
	public Request(HttpServletRequest request){
		this.request = request;		
	}
	
	public Address getAddress(){
		return new AddressParser(request);
	}

	public String getValue(String string){
		return request.getParameter(string);
	}

	public String getPath(){
		return request.getQueryString();
	}


	public String getMethod() {
		return request.getMethod();
	}


	public InputStream getInputStream() throws IOException {
		return request.getInputStream();
	}


	public HttpSession getSession() {
		return request.getSession();
	}


	public String getParameter(String string) {
		return request.getParameter(string);
	}
   
}
