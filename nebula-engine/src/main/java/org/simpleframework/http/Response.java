/*
 * Response.java February 2001
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
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Response {

	final HttpServletResponse response;
	public Response(HttpServletResponse response){
		this.response = response;		
	}
	
	
	public void setCode(int i){
		response.setStatus(i);
	}

	public void set(String string, String string2){
		response.addHeader(string, string2);
	} 
	public void set(String string, int string2){
		response.addIntHeader(string, string2);
	}

	public void setDate(String string, long lastModified){
		response.setDateHeader(string, lastModified);
	}

	public void close() throws IOException{
		response.flushBuffer();
	}

	public ServletOutputStream getPrintStream() throws IOException{
		return response.getOutputStream();
	}


	public ServletOutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}


	public void setCookie(String name,String value) {
		response.addCookie(new Cookie(name, value));
		
	}
}
