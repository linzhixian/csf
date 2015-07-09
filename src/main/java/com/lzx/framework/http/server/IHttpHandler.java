package com.lzx.framework.http.server;


public interface IHttpHandler {

	byte[] processRequest(String uri) throws Exception;
	public String getContentType();
}
