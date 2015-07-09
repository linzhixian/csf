package com.lzx.framework.socket;

import com.lzx.framework.http.server.IHttpHandler;

public abstract class SocketServerFactory {

	
	public abstract ISocketServer createSocketServer(SocketServerInitializer socketServerInitializer);
	public abstract ISocketServer createFlashSecurityServer(int port);
	public abstract ISocketServer createHttpServer(int port ,IHttpHandler handler);

}
