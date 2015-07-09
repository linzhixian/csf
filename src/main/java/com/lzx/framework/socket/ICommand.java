package com.lzx.framework.socket;


public interface ICommand {
	public IMessage doAction(ISession session,IMessage message);
}
