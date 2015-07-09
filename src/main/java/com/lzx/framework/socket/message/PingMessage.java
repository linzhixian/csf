package com.lzx.framework.socket.message;

import org.springframework.stereotype.Service;

import com.lzx.framework.socket.AbstractMessage;

@Service("pingMessage_MessageLZX")
public class PingMessage extends AbstractMessage {

	public PingMessage() {
		this.setCmd(6);
	}

	@Override
	public byte[] encode() throws Exception {
		return new byte[0];
	}

	@Override
	public void decode(byte[] data) throws Exception {
	}

}
