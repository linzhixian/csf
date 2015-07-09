package com.lzx.framework.socket;


public interface SocketServerInitializer {

	int getPort();
	public IMessageDispatch getMessageDispatch();
	public ISocketListener getSocketListener() ;
	public int getWorkThreadCount();
	public CodecFactory getCodecFactory();
	public IMessage getPingMessage();
}
