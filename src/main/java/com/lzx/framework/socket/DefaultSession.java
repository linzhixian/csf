package com.lzx.framework.socket;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DefaultSession implements ISession {

	private IChannel channel;
	private final ConcurrentHashMap<Object, Object> attachment;

	public DefaultSession(IChannel channel) {
		this.channel = channel;
		attachment = new ConcurrentHashMap<Object, Object>(3);
	}

	@Override
	public boolean isConnected() {
		if (this.channel == null) {
			return false;
		}
		return this.channel.isConnected();
	}

	@Override
	public boolean isOpen() {
		if (this.channel == null) {
			return false;
		}
		return this.channel.isOpen();
	}
	@Override
	public void disconnect() {
		if (this.channel != null && this.channel.isConnected()) {
			this.channel.disconnect();
		}
	}

	@Override
	public SocketAddress getLocalAddress() {
		if (this.channel != null) {

			return this.channel.getLocalAddress();
		}
		return null;
	}

	@Override
	public SocketAddress getRemoteAddress() {
		if (this.channel != null) {

			return this.channel.getRemoteAddress();
		}
		return null;
	}

	@Override
	public boolean send(IMessage message) {
		if (this.channel != null) {
			if (message.isCloseAfterSend()) {
				this.channel.writeThenClose(message);
			} else {
				this.channel.write(message);
			}
			return true;
		}
		return false;
	}

	@Override
	public void putAttachment(Object key, Object value) {
		attachment.put(key, value);
	}

	@Override
	public Object getAttachment(Object key) {
		return attachment.get(key);
	}

	@Override
	public Object removeAttachment(Object key) {
		return attachment.remove(key);
	}

	@Override
	public Integer getId() {
		if (this.channel != null) {

			return this.channel.getId();
		}
		return -1;

	}

	@Override
	public String toString() {
		if (this.channel == null) {

			return super.toString();
		}
		return this.channel.toString();
	}

	@Override
	public void close() {
		if (this.attachment != null) {
			this.attachment.clear();
		}
		if (this.channel != null && this.channel.isOpen()) {
			this.channel.closeAwaitUninterruptibly(5, TimeUnit.SECONDS);
		}
	}

	@Override
	public void setChannel(IChannel channel) {
		this.channel = channel;
	}

	@Override
	public IChannel getChannel() {
		return this.channel;
	}

}
