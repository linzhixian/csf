package com.lzx.framework.web.springjson.adminapi;

import java.util.List;

public interface IADiamondRecoordService {
    public List selectDiamondRecordList(String gamename, int uid, String aftertime, String beforetime) throws UnSupportFunctionException;
}
