package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzx.framework.web.springjson.beans.IUidSupport;

public class UserFeedBack implements Serializable, IUidSupport {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer uid;
    private String rolename;
    private String content;
    private String contacts;
    private String reply;
    private Date rtime;

    public Integer getUid() {
	return uid;
    }

    public void setUid(Integer uid) {
	this.uid = uid;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getContacts() {
	return contacts;
    }

    public void setContacts(String contacts) {
	this.contacts = contacts;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getReply() {
	return reply;
    }

    public void setReply(String reply) {
	this.reply = reply;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")  
    public Date getRtime() {
	return rtime;
    }

    public void setRtime(Date rtime) {
	this.rtime = rtime;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}
