package com.lzx.framework.socket;




public interface ISocketListener {
	
	/**
	 * 与对端的连接建立
	 * @param session
	 */
	void connected(IChannel channel);
	
	/**
	 * 接收到对端消息
	 * @param session 消息来源会话
	 * @param message 会话发送的消息
	 */
	void messageReceived(int sessionId,IMessage message);
	
	/**
	 * 与对端的连接断开
	 * @param session
	 */
	void disconnected(int sessionId);
	
	/**
	 * 关闭连接
	 * @param session
	 */
	void close(int sessionId);
	
	/**
	 * 触发链路检测
	 * @param connection 链路检测超时的连接
	 */
	void keepAliveTest(int sessionId);

	/**
	 * 通道发生异常将触发该方法
	 * @param conn
	 * @param cause
	 */
	void exceptionCaught(int sessionId, Throwable cause);
	
	

}
