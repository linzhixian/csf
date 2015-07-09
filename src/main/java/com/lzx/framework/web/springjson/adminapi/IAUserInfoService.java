package com.lzx.framework.web.springjson.adminapi;

import java.util.Date;

public interface IAUserInfoService {
    public Object gameSelectUserInfoId(String gamename, String name, Integer uid, Integer devicetype) throws UnSupportFunctionException;

    public void updateUser(String gamename, Integer uid, Integer sealnum, Date seal_time) throws UnSupportFunctionException;

    public Object selectIdList(String gamename,Integer id) throws UnSupportFunctionException;

}
