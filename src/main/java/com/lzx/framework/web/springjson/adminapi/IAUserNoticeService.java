package com.lzx.framework.web.springjson.adminapi;

import java.util.Date;
import java.util.List;

public interface IAUserNoticeService {

    public List selectNoticeList(String gamename,Integer id, Integer type) throws UnSupportFunctionException;

    public int deleteNoticeList(String gamename,Integer id) throws UnSupportFunctionException;

    public int insertNoticeList(String gamename,Integer type, String content, Integer tigger, Integer repeatnum, Integer scrolltime, Integer intervaltime, Integer iscycle,
	    Date starttime, Date endtime) throws UnSupportFunctionException;

    public int updateNoticeList(String gamename,Integer id, Integer type, String content, Integer tigger, Integer repeatnum, Integer scrolltime, Integer intervaltime, Integer iscycle,
	    Date starttime, Date endtime) throws UnSupportFunctionException;
    
}
