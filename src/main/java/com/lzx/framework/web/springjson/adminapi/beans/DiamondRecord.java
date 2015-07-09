package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DiamondRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mark;
    private int id;
    private int uid;

    private String code;
    private int pid;
    private int num;
    private Date time;
    private int cost;
    private String beforetime;
    private String aftertime;
    private String transaction;
    private Integer gameid;

    public String getMark() {
	return mark;
    }

    public void setMark(String mark) {
	this.mark = mark;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public int getPid() {
	return pid;
    }

    public void setPid(int pid) {
	this.pid = pid;
    }

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }

    public int getUid() {
	return uid;
    }

    public void setUid(int uid) {
	this.uid = uid;
    }

    public String getBeforetime() {
	return beforetime;
    }

    public void setBeforetime(String beforetime) {
	this.beforetime = beforetime;
    }

    public String getAftertime() {
	return aftertime;
    }

    public void setAftertime(String aftertime) {
	this.aftertime = aftertime;
    }

    public String getTransaction() {
	return transaction;
    }

    public void setTransaction(String transaction) {
	this.transaction = transaction;
    }

    public Integer getGameid() {
	return gameid;
    }

    public void setGameid(Integer gameid) {
	this.gameid = gameid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static void main(String[] args) {
	Date d=new Date();
	d.setTime(1431481315000l);
	System.out.println(d);
    }
}
