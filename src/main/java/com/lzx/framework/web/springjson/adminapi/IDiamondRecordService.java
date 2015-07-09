package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IDiamondRecordService {
    public List selectDiamondRecordList(int uid, String aftertime, String beforetime);
}
