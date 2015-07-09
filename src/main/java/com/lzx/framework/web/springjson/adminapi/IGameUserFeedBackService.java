package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IGameUserFeedBackService {

    public List queryList(String timeFrom, String timeTo, int reply, Integer deviceType);

    public void updateReply(Integer id, Integer uid, String reply, String rtime);
}
