package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IAUserPropAssignService {
    public List selectUserInfoId(String gamename,Integer uid) throws UnSupportFunctionException;

    public List selectGamePropList(String gamename,Integer uid, Integer id) throws UnSupportFunctionException;

    public void updatePropList(String gamename,Integer uid, Integer id, Integer num) throws UnSupportFunctionException;

    public void insertPropList(String gamename,Integer uid, Integer id, Integer num) throws UnSupportFunctionException;
}
