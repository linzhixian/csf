package com.lzx.framework.socket;

/**
 * SocketServer接口：用于不用的实现，例如：Netty3，Netty4，Mina
 * @author lzx
 *
 */
public interface ISocketServer {

	void close();

	void bind() throws InterruptedException;

}
