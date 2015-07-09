package com.lzx.framework.web.springjson.adminapi;

import java.util.Date;
import java.util.List;


public interface IUserNoticeService {
    public List selectNoticeList(Integer id, Integer type);

    public int deleteNoticeList(Integer id);

    public int insertNoticeList(Integer type, String content, Integer tigger, Integer repeatnum, Integer scrolltime, Integer intervaltime, Integer iscycle,
	    Date starttime, Date endtime);

    public int updateNoticeList(Integer id, Integer type, String content, Integer tigger, Integer repeatnum, Integer scrolltime, Integer intervaltime, Integer iscycle,
	    Date starttime, Date endtime);
}
