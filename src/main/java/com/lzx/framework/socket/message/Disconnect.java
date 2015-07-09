package com.lzx.framework.socket.message;

import org.springframework.stereotype.Service;

import com.lzx.framework.socket.AbstractMessage;

@Service("disconnect_MessageLZX")
public class Disconnect extends AbstractMessage{

	private static Disconnect instance=new Disconnect();


	public Disconnect() {
		this.setCmd(10000);
	}
	public static Disconnect getInstance() {
		return instance;
	}
	@Override
	public byte[] encode() {
		return null;
	}

	@Override
	public void decode(byte[] content) throws Exception {
		
	}



}
