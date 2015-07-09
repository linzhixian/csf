package com.lzx.framework.socket;

public interface IMessage extends IInnerEnable {

	public int getCmd();
	public void setCmd(int cmd);
	
	public byte[] encode() throws Exception;

	public void decode(byte[] data) throws Exception;
	
	public boolean isCloseAfterSend();
	public void setCloseAfterSend(boolean closeAfterSend);
}

