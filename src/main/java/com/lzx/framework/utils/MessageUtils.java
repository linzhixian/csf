package com.lzx.framework.utils;

import com.lzx.framework.server.socket.NioServer;
import com.lzx.framework.socket.IMessage;

public class MessageUtils {

	public static IMessage newMessage(Class mClass) {
		try {

			Integer cmd = NioServer.COMMANDS_IDS_MAP.get(mClass.getSimpleName());
			if (cmd != null) {
				IMessage msg = (IMessage) mClass.newInstance();
				msg.setCmd(cmd);
				return msg;
			} else {
				throw new java.lang.RuntimeException();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
}
