package com.lzx.framework.socket.codec;

import java.io.IOException;
import java.io.OutputStream;

public class CheckSumStream extends OutputStream{

    private int sum;
    
    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(byte b[], int off, int len){
        sum = 0;
        for (int i = off + len; --i >= off; ){
            sum += b[i];
        }
        sum = sum & 0xff;
    }
    
    public int getCheckSum(){
        return sum;
    }
}
