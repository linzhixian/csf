package com.lzx.framework.web.springjson.beans;

import java.util.Map;

public interface IRequestJson {

    void setSource(String body);

    Integer getUid();

    String getSign();

    String getSource();

    IRequestJson getValue();

    void setUid(Integer uid);

    void setHeader(Map<String, String> header);
    Map<String, String>  getHeader();
}
