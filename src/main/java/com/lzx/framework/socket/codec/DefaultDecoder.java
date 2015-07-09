/** Date:2013-1-24下午2:07:37 Copyright (c) 2013, 三三得玖 All Rights Reserved.
 */
package com.lzx.framework.socket.codec;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.Length;

public class DefaultDecoder extends AbstractDecoder {


	public DefaultDecoder(IMessageDispatch messageDispath) {
		super(messageDispath);
	}

	@Override
	public IMessage decode(IChannelBuffer buffer) throws Exception {
		// Mark begin index
		//buffer.markReaderIndex();

		int packetLength = buffer.readUnsignedShort();
		if (packetLength < Length.HEADER_LENGTH) {
			throw new RuntimeException("Invalid packet length:[" + packetLength + "]");
		}
		if (buffer.readableBytes() < packetLength - Length.SHORT2) {
			buffer.resetReaderIndex();
			return null;
		}


		int bodyLength = packetLength - (Length.HEADER_LENGTH);
		byte[] bodyBytes = buffer.readBytes(bodyLength).array();
		/* byte[] desBytes = buffer.readBytes(Length.DES_LENGTH).array(); */
		// int crcValue = buffer.readInt();
		// crc32校验
		// checkCrc32(bodyBytes, crcValue);
		// des校验
		// checkDes(bodyBytes,desBytes);
		// 解压�?
		// bodyBytes = appContext.uncompress(commandId, bodyBytes);

		// RequestId
		int commandId = buffer.readUnsignedShort();
		IMessage message = extractMessage(bodyBytes, commandId);
		//System.out.println("decoder:" + message);
		return message;
	}




}
