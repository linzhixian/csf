package com.lzx.framework.socket;

public abstract class AbstractMessage implements IMessage {

	private int cmd;

	//是否在发送完该消息即关闭
	private boolean closeAfterSend=false;
	@Override
	public int getCmd() {
		return cmd;
	}

	@Override
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public Object innerObj() {
		return this;
	}
	@Override
	public boolean isCloseAfterSend() {
		return closeAfterSend;
	}
	@Override
	public void setCloseAfterSend(boolean closeAfterSend) {
		this.closeAfterSend = closeAfterSend;
	}
	
}
