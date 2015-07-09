package com.lzx.framework.rmi;
import java.rmi.server.*;
import java.io.*;
import java.net.*;
public class MyRMISocket extends RMISocketFactory {
	private int port;
	public MyRMISocket(int port){
		this.port = port;
	}

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		if (port == 0)
            port = this.port;
        return new ServerSocket(port);
	}
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return new Socket(host,port);
	}

}