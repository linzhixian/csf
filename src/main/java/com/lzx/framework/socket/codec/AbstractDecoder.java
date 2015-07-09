package com.lzx.framework.socket.codec;

import java.util.zip.CRC32;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.IDecoder;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.exception.CrcWrongException;

public abstract class AbstractDecoder implements IDecoder {

	protected IMessageDispatch messageDispath;

	public AbstractDecoder() {

	}

	public AbstractDecoder(IMessageDispatch messageDispath) {
		this.messageDispath = messageDispath;
	}

	@Override
	public abstract IMessage decode(IChannelBuffer buffer) throws Exception;

	protected IMessage extractMessage(byte[] bodyBytes, int commandId) throws InstantiationException, IllegalAccessException,
			Exception {
		Class<IMessage> messageClass = messageDispath.getMessageClassByCmd(commandId);
		if (messageClass == null) {
			throw new RuntimeException("Can't find MessageClass for commandId:[" +commandId + "]");
			//System.err.println("Can't find MessageClass for commandId:[" +commandId + "]");
			//return null;
		}
		IMessage message = messageClass.newInstance();
		message.setCmd((short) commandId);
		message.decode(bodyBytes);
		return message;
	}

	/**
	 * 描述:如方法名. <br/>
	 * 
	 * @author Administrator
	 * @param bodyBytes
	 * @param crcValue
	 * @throws CrcWrongException
	 */
	private void checkCrc32(byte[] bodyBytes, int crcValue) throws CrcWrongException {
		// �?��crc32完整校验�?
		CRC32 crc32 = new CRC32();
		crc32.update(bodyBytes);
		int targetValue = (int) crc32.getValue();
		if (targetValue != crcValue) {
			throw new CrcWrongException();
		}
	}
}
