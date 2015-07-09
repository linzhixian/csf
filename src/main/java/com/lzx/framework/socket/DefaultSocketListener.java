package com.lzx.framework.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class DefaultSocketListener implements ISocketListener {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSocketListener.class);
	
	@Override
	public abstract void connected(IChannel channel) ;
	public abstract ISession getSessionById(int sessionId) ;
	@Override
	public abstract void close(int sessionId);
	public abstract IMessageDispatch getMessageDispatch();
	
	@Override
	public void messageReceived(int sessionId, IMessage message) {
		ISession session =getSessionById(sessionId);
		if (session == null) {
			logger.error("not find session for id:" + sessionId);
			return;
		}
		IMessage resMessage = getMessageDispatch().dispatch(session, message);
		if (resMessage != null) {
			session.send(resMessage);
			
		}

	}

	@Override
	public void disconnected(int sessionId) {
		ISession session =getSessionById(sessionId);
		session.disconnect();
	}

	@Override
	public void keepAliveTest(int sessionId) {

	}

	@Override
	public void exceptionCaught(int sessionId, Throwable cause) {
		cause.printStackTrace();
	}


}
