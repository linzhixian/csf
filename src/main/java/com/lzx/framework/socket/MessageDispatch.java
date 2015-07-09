package com.lzx.framework.socket;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息分配器
 * @author lzx
 *
 */
public class MessageDispatch implements IMessageDispatch {

	private static final Logger logger = LoggerFactory.getLogger(MessageDispatch.class);
	
	private Map<Integer, MessageClassAction> registeredCommandsMap = new HashMap<Integer, MessageClassAction>();

	public void registerCommandAction(int cmd,ICommand c) {
		MessageClassAction mca=getMessageClassAction(cmd);
		if (mca.getCommand()!= null) {
			logger.error("cmd=" + cmd + ",CommandAction  duplicate  be registered.");
			return;
		} 
		mca.setCommand(c);
	}
	public void registerMessageClass(int cmd, Class messageClass) {
		MessageClassAction mca=getMessageClassAction(cmd);
		if(mca.getMessageClass()!=null) {
			logger.error("cmd=" + cmd + ",MessageClass has be registered.");
			return;
		} 
	mca.setMessageClass(messageClass);	
	
	}
	public void registerMessage(int cmd, Class messageClass, ICommand command) {
		//logger.info("RegisterMessage:cmd="+cmd+",messageClass="+messageClass+",command="+command);
		this.registerCommandAction(cmd, command);
		this.registerMessageClass(cmd, messageClass);
	}
	private MessageClassAction getMessageClassAction(int cmd) {
		MessageClassAction c=registeredCommandsMap.get(cmd);
		if(c==null)  {
			c=new  MessageClassAction();
			registeredCommandsMap.put(cmd, c);
		}
		return c;
	}
	/* (non-Javadoc)
	 * @see com.lzx.framework.server.socket.IMessageDispatch#dispatch(com.lzx.framework.socket.Session, com.lzx.framework.socket.message.IMessage)
	 */
	@Override
	public IMessage dispatch(ISession session,IMessage msg) {
		MessageClassAction classAction = registeredCommandsMap.get(msg.getCmd());
		
		if (classAction==null || classAction.getCommand() == null) {
			logger.error("can't find Command for cmd:" + msg.getCmd());
			return null;
		}
		return classAction.getCommand().doAction(session,msg);
	}

	public Class getMessageClassByCmd(int cmd) {
		MessageClassAction classAction = registeredCommandsMap.get(cmd);
		if (classAction != null)
			return classAction.getMessageClass();
		else
			return null;
	}

}

class MessageClassAction {
	Class messageClass;
	ICommand command;

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public MessageClassAction(Class messageClass, ICommand command) {
		this.messageClass = messageClass;
		this.command = command;
	}

	public MessageClassAction() {
	}

	public Class getMessageClass() {
		return messageClass;
	}

	public void setMessageClass(Class messageClass) {
		this.messageClass = messageClass;
	}

}