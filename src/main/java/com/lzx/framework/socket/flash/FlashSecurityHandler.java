package com.lzx.framework.socket.flash;

import java.io.UnsupportedEncodingException;

import com.lzx.framework.socket.IChannel;
import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.ICreateBuffer;

public class FlashSecurityHandler {
	
    private int cummulated = 0;
    private IChannelBuffer sendBuffer;
    private static byte[] SECURITY;
    static {
    	
          
    	  String policy = "<cross-domain-policy>\n"
                  + "<allow-access-from domain=\"*\" to-ports=\"*\"/>\n"
                  + "</cross-domain-policy>\n";
    	  try {
			SECURITY=policy.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
    public FlashSecurityHandler(ICreateBuffer createBuffer) {
    	sendBuffer=createBuffer.createChannelBuffer();
    	sendBuffer.writeBytes(SECURITY);
    }
    
    public void messageReceived(IChannel channel,IChannelBuffer buffer) throws Exception {
                
        cummulated += buffer.readableBytes();
        
        if (cummulated >= 22){
        	channel.writeThenClose(sendBuffer);
        }
    }

}
