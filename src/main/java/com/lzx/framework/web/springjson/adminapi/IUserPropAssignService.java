package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IUserPropAssignService {
    public List selectUserInfoId(Integer uid);

    public List selectGamePropList(Integer uid, Integer id);

    public void updatePropList(Integer uid, Integer id, Integer num);

    public void insertPropList(Integer uid, Integer id, Integer num);
}
