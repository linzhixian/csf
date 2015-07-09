package com.lzx.framework.socket;

import com.lzx.framework.socket.codec.DefaultDecoder;
import com.lzx.framework.socket.codec.DefaultEncoder;

public class CodecFactory {

	public static CodecFactory defaultFactory;
	
	public static CodecFactory getDefault() {
		if(defaultFactory==null) {
			defaultFactory=new CodecFactory();
		}
		return defaultFactory;
	}

	public IEncoder createEncoder(ICreateBuffer createBuffer) {
		return new DefaultEncoder(createBuffer);
	}

	public IDecoder createDecoder(IMessageDispatch messageDispatch) {
		return new DefaultDecoder(messageDispatch);
	}
}
