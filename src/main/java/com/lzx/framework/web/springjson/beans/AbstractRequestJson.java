package com.lzx.framework.web.springjson.beans;

import java.util.Map;

import com.lzx.framework.web.springjson.annotation.WebActionIngore;

public class AbstractRequestJson implements IRequestJson {
    private String sign;

    private String source;

    private Integer uid;

    private IRequestJson value;

    private Map<String, String> header;

    public IRequestJson getValue() {
	return value;
    }

    public void setValue(IRequestJson value) {
	this.value = value;
    }

    @WebActionIngore
    public Integer getUid() {
	return uid;
    }

    public void setUid(Integer uid) {
	this.uid = uid;
    }
    @WebActionIngore
    public String getSign() {
	return sign;
    }

    public void setSign(String sign) {
	this.sign = sign;
    }

    @WebActionIngore
    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    @Override
    public void setHeader(Map<String, String> header) {
	this.header=header;
    }
    @WebActionIngore
    @Override
    public Map<String, String> getHeader() {
	return this.header;
    }
}
