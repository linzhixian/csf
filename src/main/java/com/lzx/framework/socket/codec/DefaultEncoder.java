package com.lzx.framework.socket.codec;

import java.util.zip.CRC32;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.ICreateBuffer;
import com.lzx.framework.socket.IEncoder;
import com.lzx.framework.socket.IMessage;

/**
 * <pre>
 * 消息结构
 * +-------------------------+-------+-------------+
 * |         Header          | body  |       |
 * |-------------+-----------+---------------------+
 * |PacketLength | CommandID | body  |  |
 * +-------------+-----------+-------+-------------+
 * 各个数据项的长度
 * PacketLength=2 bit   为整个消息的长度
 * CommandID=2 bit
 * body= change
 * </pre>
 * 
 * 
 * date: 2013-1-24 下午2:08:01 <br/>
 * 
 * @author lzx
 * @version
 * @since JDK 1.6
 */
public class DefaultEncoder implements IEncoder {

	private ICreateBuffer createBuffer ;
	
	public DefaultEncoder(ICreateBuffer createBuffer) {
		this.createBuffer=createBuffer;
	}


	@Override
	public Object encode(Object msg) throws Exception {
		if (!(msg instanceof IMessage)) {
			return msg;
		}
		IMessage msgObj = (IMessage) msg;
		int commandId = msgObj.getCmd();

		byte[] bodyBytes = msgObj.encode();
		if(bodyBytes==null) bodyBytes=new byte[0];
		int bodyLength = bodyBytes.length;

		IChannelBuffer buffer = createBuffer.createChannelBuffer();
		buffer.writeShort(0);//先占位
		buffer.writeShort(commandId);
		buffer.writeBytes(bodyBytes);
		buffer.setShort(0, buffer.writerIndex());//写消息总长度
/*		// 写消息安全码
		writeMac(buffer, bodyBytes);*/
		return buffer.innerObj();
	}



	private void writeMac(IChannelBuffer buffer, byte[] bodyBytes) {
		// 生成crc32完整校验�?
		CRC32 crc32 = new CRC32();
		crc32.update(bodyBytes);
		buffer.writeInt((int) crc32.getValue());

	}

}
