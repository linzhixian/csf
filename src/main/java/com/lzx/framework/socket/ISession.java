package com.lzx.framework.socket;

import java.net.SocketAddress;

import javax.mail.Message;




public interface ISession {
	
	/**
	 * 获取Session id
	 * @return Integer
	 */
	Integer getId();
	/**
	 * 会话连接是否建立
	 * @return
	 */
	boolean isConnected();
	
	public boolean isOpen() ;
	/**
	 * 断开当前会话连接
	 */
	void disconnect();
	
	/**
	 * 关闭会话
	 */
	void close();
	
	/**
	 * 获取本地地址
	 * @return
	 */
	SocketAddress getLocalAddress();
	
	/**
	 * 获取对端地址
	 * @return
	 */
	SocketAddress getRemoteAddress();
	
	/**
	 * 向对端发送消息
	 * @param message
	 */
	boolean send(IMessage message);
	

	
	/**
	 * 添加附件
	 * @param key
	 * @param value
	 */
	void putAttachment(Object key,Object value);
	
	/**
	 * 获取附件
	 * @param key
	 * @return
	 */
	Object getAttachment(Object key);
	
	/**
	 * 删除附件
	 * @param key
	 * @return
	 */
	Object removeAttachment(Object key);
	
	
	void setChannel(IChannel channel);
	IChannel getChannel();
	
/*	public void setChannel(Channel channel);
	public Channel getChannel();*/

}
