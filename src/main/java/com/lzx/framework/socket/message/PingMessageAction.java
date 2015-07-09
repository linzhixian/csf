package com.lzx.framework.socket.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;

@Service("pingMessageAction_ActionLZX")
public class PingMessageAction implements ICommand {
	private static final Logger logger = LoggerFactory.getLogger(PingMessageAction.class);

	@Override
	public IMessage doAction(ISession session, IMessage message) {
		return null;
	}

}
