package com.lzx.framework.socket;


public class DefaultClientSocketListener extends DefaultSocketListener {

	IMessageDispatch messageDispatch;
	
	ISession session;
	public DefaultClientSocketListener(IMessageDispatch messageDispatch) {
		this.messageDispatch=messageDispatch;
	}

	@Override
	public void connected(IChannel channel) {
		session=new DefaultSession(channel);
	}

	@Override
	public ISession getSessionById(int sessionId) {
		return session;
	}

	@Override
	public void close(int sessionId) {
		session.close();
	}

	@Override
	public IMessageDispatch getMessageDispatch() {
		return messageDispatch;
	}

}
