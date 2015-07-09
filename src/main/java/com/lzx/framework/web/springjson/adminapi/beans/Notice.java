package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer type;
    private Integer tigger;
    private Integer repeatnum;
    private Integer scrolltime;
    private Integer intervaltime;
    private Integer iscycle;
    private String content;
    private Date time;
    private Date starttime;
    private Date endtime;
    private int version;
    private String status;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getType() {
	return type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public Integer getTigger() {
	return tigger;
    }

    public void setTigger(Integer tigger) {
	this.tigger = tigger;
    }

    public Integer getRepeatnum() {
	return repeatnum;
    }

    public void setRepeatnum(Integer repeatnum) {
	this.repeatnum = repeatnum;
    }

    public Integer getScrolltime() {
	return scrolltime;
    }

    public void setScrolltime(Integer scrolltime) {
	this.scrolltime = scrolltime;
    }

    public Integer getIntervaltime() {
	return intervaltime;
    }

    public void setIntervaltime(Integer intervaltime) {
	this.intervaltime = intervaltime;
    }

    public Integer getIscycle() {
	return iscycle;
    }

    public void setIscycle(Integer iscycle) {
	this.iscycle = iscycle;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

    public int getVersion() {
	return version;
    }

    public void setVersion(int version) {
	this.version = version;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getEndtime() {
	return endtime;
    }

    public void setEndtime(Date endtime) {
	this.endtime = endtime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getStarttime() {
	return starttime;
    }

    public void setStarttime(Date starttime) {
	this.starttime = starttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
