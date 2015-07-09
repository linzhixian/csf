package com.lzx.framework.socket;

import java.io.IOException;
import java.io.OutputStream;

public interface IChannelBuffer extends IInnerEnable{

	void writeShort(int i);

	void writeBytes(byte[] bodyBytes);

	int writerIndex();

	void setShort(int index, int value);

	void writeInt(int value);

	int readableBytes();

	void markReaderIndex();

	int readUnsignedShort();

	void resetReaderIndex();

	IChannelBuffer readBytes(int bodyLength);

	byte[] array();

	void writeByte(int i);

	void setByte(int i, int offsetByte);

	int readUnsignedByte();

	int readerIndex();

	void getBytes(int readerIndex, OutputStream checkSumStream, int readableBytes) throws IOException;

	void getBytes(int readerIndex, byte[] bodyBytes);

}
