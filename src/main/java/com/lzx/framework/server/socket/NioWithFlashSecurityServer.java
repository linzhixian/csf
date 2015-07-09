package com.lzx.framework.server.socket;

import com.lzx.framework.socket.ISocketServer;
import com.lzx.framework.socket.SocketServerFactory;

public class NioWithFlashSecurityServer extends NioServer {

	private int flashSecurityPort;
	
	private ISocketServer flashSecurityServer;
	
	@Override
	public void start() {
		super.start();
		startFlashSecurity();
	}

	public void startFlashSecurity() {
		SocketServerFactory socketServerFactory = (SocketServerFactory) this.getApplicationContext().getBean(
				"socketServerFactory");
		flashSecurityServer = socketServerFactory.createFlashSecurityServer(flashSecurityPort);
				
		try {
			flashSecurityServer.bind();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public int getFlashSecurityPort() {
		return flashSecurityPort;
	}

	public void setFlashSecurityPort(int flashSecurityPort) {
		this.flashSecurityPort = flashSecurityPort;
	}
	@Override
	public void stop() {
		super.stop();
		if (flashSecurityServer != null) {
			flashSecurityServer.close();
		}
	}
}
