package com.lzx.framework.web.springjson.framework.jsonbean;

public class ResetPwd {
    private Integer id;
    private String newpassword;

    public ResetPwd() {

    }

    public ResetPwd(int id,String pwd) {
	this.id=id;
	this.newpassword=pwd;
    }
    public ResetPwd(int id) {
	this.id=id;
    }
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNewpassword() {
	return newpassword;
    }

    public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
    }
}
