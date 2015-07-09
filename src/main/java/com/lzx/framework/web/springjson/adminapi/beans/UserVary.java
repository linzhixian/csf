package com.lzx.framework.web.springjson.adminapi.beans;

import java.io.Serializable;
import java.util.Date;

public class UserVary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;
    private Integer exp;
    private Integer age;
    private Integer gold;
    private Integer diamond;
    private Integer strength;
    private Integer bone;
    private Integer piece;
    private Integer cup;
    private Integer cuplevel;
    private Integer runcount;
    private Integer challengecount;
    private Integer getgold;
    private Integer bosscount;
    private Integer techpoint;
    private Integer totalrunnum;//总跑距离
    private Date lastupdatestrengthtime;

    public UserVary() {

    }

    public UserVary(Integer uid) {
	this.uid = uid;
    }

    public Integer getUid() {
	return uid;
    }

    public void setUid(Integer uid) {
	this.uid = uid;
    }

    public Integer getExp() {
	return exp;
    }

    public void setExp(Integer exp) {
	this.exp = exp;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public Integer getGold() {
	return gold;
    }

    public void setGold(Integer gold) {
	this.gold = gold;
    }

    public Integer getDiamond() {
	return diamond;
    }

    public void setDiamond(Integer diamond) {
	this.diamond = diamond;
    }

    public Integer getStrength() {
	return strength;
    }

    public void setStrength(Integer strength) {
	this.strength = strength;
    }

    public Integer getBone() {
	return bone;
    }

    public void setBone(Integer bone) {
	this.bone = bone;
    }

    public Integer getPiece() {
	return piece;
    }

    public void setPiece(Integer piece) {
	this.piece = piece;
    }

    public Integer getRuncount() {
	return runcount;
    }

    public void setRuncount(Integer runcount) {
	this.runcount = runcount;
    }

    public Integer getChallengecount() {
	return challengecount;
    }

    public void setChallengecount(Integer challengecount) {
	this.challengecount = challengecount;
    }

    public Date getLastupdatestrengthtime() {
	return lastupdatestrengthtime;
    }

    public void setLastupdatestrengthtime(Date lastupdatestrengthtime) {
	this.lastupdatestrengthtime = lastupdatestrengthtime;
    }

    public Integer getCup() {
	return cup;
    }

    public void setCup(Integer cup) {
	this.cup = cup;
    }

    public Integer getCuplevel() {
	return cuplevel;
    }

    public void setCuplevel(Integer cuplevel) {
	this.cuplevel = cuplevel;
    }

    public Integer getGetgold() {
	return getgold;
    }

    public void setGetgold(Integer getgold) {
	this.getgold = getgold;
    }

    public Integer getBosscount() {
	return bosscount;
    }

    public void setBosscount(Integer bosscount) {
	this.bosscount = bosscount;
    }

    public Integer getTechpoint() {
	return techpoint;
    }

    public void setTechpoint(Integer techpoint) {
	this.techpoint = techpoint;
    }

    public Integer getTotalrunnum() {
	return totalrunnum;
    }

    public void setTotalrunnum(Integer totalrunnum) {
	this.totalrunnum = totalrunnum;
    }

}
