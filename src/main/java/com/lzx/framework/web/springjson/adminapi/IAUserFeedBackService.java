package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IAUserFeedBackService {

    public void updateReply(String gamename, Integer id, Integer uid, String reply, String rtime) throws UnSupportFunctionException;

    public List queryList(String gamename, String timeFrom, String timeTo, int reply, Integer deviceType) throws UnSupportFunctionException;
}
