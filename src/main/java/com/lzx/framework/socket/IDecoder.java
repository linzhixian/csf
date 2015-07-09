package com.lzx.framework.socket;

public interface IDecoder {
	   public IMessage decode(IChannelBuffer buffer) throws Exception;
}
