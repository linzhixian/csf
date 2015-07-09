package com.lzx.framework.web.springjson.adminapi;

public interface IAUserMessageService {
    public boolean sendSystemUserMessage(String gamename,Integer uid, String msg) throws UnSupportFunctionException;
}
