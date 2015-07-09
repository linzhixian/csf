package com.lzx.framework.socket;

public class AdapterMessage extends AbstractMessage{

	public static byte[] EMPTY_BYTE=new byte[0];
	@Override
	public byte[] encode() throws Exception {
		return EMPTY_BYTE;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		
	}

}
