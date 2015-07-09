package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String account;
    private String password;
    private String rolename;
    private String md5key;

    private int platformid;
    private String openid;
    private String figureurl_qq;
    private String qqname;
    private Date create_time;
    private Date seal_time;
    private Integer gameid;
    private Integer seal;

    private Integer devicetype;

    public UserInfo() {

    }

    public UserInfo(Integer uid, String password) {
	this.id = uid;
	this.password = password;
    }

    public Integer getId() {
	return id;
    }

    public Integer getUid() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getAccount() {
	return account;
    }

    public void setAccount(String account) {
	this.account = account;
    }

    public String getRolename() {
	return rolename;
    }

    public void setRolename(String rolename) {
	this.rolename = rolename;
    }

    public String getMd5key() {
	return md5key;
    }

    public void setMd5key(String md5key) {
	this.md5key = md5key;
    }

    public String toString() {
	return "{\"id\":" + id + ",\"account\":\"" + account + "\"" + "}";
    }

    public String getOpenid() {
	return openid;
    }

    public void setOpenid(String openid) {
	this.openid = openid;
    }

    public int getPlatformid() {
	return platformid;
    }

    public void setPlatformid(int platformid) {
	this.platformid = platformid;
    }

    public String getFigureurl_qq() {
	return figureurl_qq;
    }

    public void setFigureurl_qq(String figureurl_qq) {
	this.figureurl_qq = figureurl_qq;
    }

    public String getQqname() {
	return qqname;
    }

    public void setQqname(String qqname) {
	this.qqname = qqname;
    }

    public Date getCreate_time() {
	return create_time;
    }

    public void setCreate_time(Date create_time) {
	this.create_time = create_time;
    }

    public Date getSeal_time() {
	return seal_time;
    }

    public void setSeal_time(Date seal_time) {
	this.seal_time = seal_time;
    }

    public Integer getGameid() {
	return gameid;
    }

    public void setGameid(Integer gameid) {
	this.gameid = gameid;
    }

    public Integer getSeal() {
	return seal;
    }

    public void setSeal(Integer seal) {
	this.seal = seal;
    }

    public Integer getDevicetype() {
	return devicetype;
    }

    public void setDevicetype(Integer devicetype) {
	this.devicetype = devicetype;
    }

}
