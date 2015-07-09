package com.lzx.framework.web.springjson.framework.daoentity;

public class UpdateAdminUser {
    private Integer id;
    private String gamename;
    private String permission;
    private String memo;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGamename() {
        return gamename;
    }
    public void setGamename(String gamename) {
        this.gamename = gamename;
    }
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
}
