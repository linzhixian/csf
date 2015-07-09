package com.lzx.framework.socket;


public interface IMessageDispatch {

	public abstract IMessage dispatch(ISession session, IMessage msg);
	public Class getMessageClassByCmd(int cmd);

}