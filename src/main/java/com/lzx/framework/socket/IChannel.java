package com.lzx.framework.socket;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;


public interface IChannel {

	boolean isConnected();

	void disconnect();

	SocketAddress getLocalAddress();

	SocketAddress getRemoteAddress();

	void write(IMessage message);
	void writeThenClose(IMessage message);
	
	boolean writeAwaitUninterruptibly(IMessage message,long timeout,TimeUnit timeUnit);

	Integer getId();

	boolean isOpen();

	void close();
	
	boolean closeAwaitUninterruptibly(int waitTime,TimeUnit timeUnit);
	
	public String toString();


	void writeThenClose(IChannelBuffer msg);
}
