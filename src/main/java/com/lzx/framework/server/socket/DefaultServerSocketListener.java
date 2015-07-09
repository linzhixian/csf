package com.lzx.framework.server.socket;

import com.lzx.framework.socket.DefaultSocketListener;
import com.lzx.framework.socket.IChannel;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.socket.message.Disconnect;

public class DefaultServerSocketListener extends DefaultSocketListener{

	protected NioServer server;
	

	public DefaultServerSocketListener(NioServer server) {
		this.server = server;
	}

	public ISession getSessionById(int sessionId) {
		return server.getSessionManager().find(sessionId);
	}
	@Override
	public void connected(IChannel channel) {
		server.getSessionManager().newSession(channel);
	}
	
	@Override
	public void close(int sessionId) {
		server.getSessionManager().sessionClose(sessionId);
	}
	public  IMessageDispatch getMessageDispatch() {
		return server.getMessageDispath();
	}
	@Override
	public void disconnected(int sessionId) {
		ISession session =getSessionById(sessionId);
		session.disconnect();
		messageReceived(sessionId,Disconnect.getInstance());
	}
}
