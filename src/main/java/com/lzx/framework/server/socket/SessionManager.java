package com.lzx.framework.server.socket;

import com.lzx.framework.socket.DefaultSession;
import com.lzx.framework.socket.IChannel;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.utils.collection.IntConcurrentHashMap;

public class SessionManager {

	protected IntConcurrentHashMap<ISession> sessionsMap = new IntConcurrentHashMap<ISession>();

	public SessionManager(NioServer nioServer) {
	}

	public void newSession(IChannel channel) {
		sessionsMap.put(channel.getId(),new DefaultSession(channel));
	}

	public ISession find(int sessionId) {
		return sessionsMap.get(sessionId);
	}

	public void sessionClose(int sessionId) {
		ISession session = this.find(sessionId);
		if (session != null)
			session.close();
		this.remove(sessionId);
	}

	private void remove(int sessionId) {
		sessionsMap.remove(sessionId);
		System.out.println("remove Session:"+sessionId);
	}

}
