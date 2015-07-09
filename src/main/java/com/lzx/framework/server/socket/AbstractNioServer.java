package com.lzx.framework.server.socket;

import com.lzx.framework.rmi.RMIUtil;
import com.lzx.framework.server.AbstractServer;
import com.lzx.framework.utils.IpTools;

public class AbstractNioServer extends AbstractServer {
	private int port;
	private String ip;
	@Override
	public void start() throws Exception {
		
	}

	@Override
	public void stop() throws Exception {
		
	}

	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	public void setDataCenterRmiUrl(String rmiurl) {
		RMIUtil.RMI_URL=rmiurl;
	}
	public String getIp() {
		if(ip==null) {
			this.ip=IpTools.findMyIp();
		}
		return this.ip;
	}
}
