package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserProp implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer uid;
    private Integer id;
    private Integer num;
    private String mark;

    public UserProp() {
    }

    public UserProp(Integer uid) {
	this.uid = uid;
    }

    public UserProp(Integer uid, Integer id,Integer num) {
	this.uid = uid;
	this.id = id;
	this.num = num;
    }

    public Integer getUid() {
	return uid;
    }

    public void setUid(Integer uid) {
	this.uid = uid;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getNum() {
	return num;
    }

    public void setNum(Integer num) {
	this.num = num;
    }

    public String getMark() {
	return mark;
    }

    public void setMark(String mark) {
	this.mark = mark;
    }

}
