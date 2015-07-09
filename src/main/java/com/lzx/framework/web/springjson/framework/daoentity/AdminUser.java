package com.lzx.framework.web.springjson.framework.daoentity;

import java.io.Serializable;

import net.sf.json.JSONArray;

public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static  final  int TYPE_ROOT=0;
    private Integer id;

    private String name;
    private String username;
    private String password;
    private int type;
    private int createid;//创建者id，-1 表示没有父id
    private String gamename;
    private String permission;

    private Object attachment;
    
    private String email;
    private String memo;
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getPermission() {
	if(permission==null) return "";
	return permission;
    }

    public void setPermission(String permission) {
	this.permission = permission;
    }

    public String[] queryPermissionArray() {
	if(permission==null) {
	    return new String[0];
	}
	JSONArray permissionJson=JSONArray.fromObject(permission);
	String[] array=new String[permissionJson.size()];
	permissionJson.toArray(array);
	return array;
    }

    public int getCreateid() {
        return createid;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public String getGamename() {
	if(gamename==null) return "";
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }
    public String[] queryGamenameArray() {
	if(gamename==null) {
	    return new String[0];
	}
	JSONArray gamenameJson=JSONArray.fromObject(gamename);
	String[] array=new String[gamenameJson.size()];
	gamenameJson.toArray(array);
	return array;
    }
    public boolean isRoot() {
	return this.type==TYPE_ROOT;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
