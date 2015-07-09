package com.lzx.framework.web.springjson.adminapi;

import java.util.Date;

public interface IUserInfoService {
    public Object gameSelectUserInfoId(String name, Integer uid, Integer devicetype);

    public void updateUser(Integer uid, Integer sealnum, Date seal_time);

    public Object selectIdList(Integer uid);
}
