package com.lzx.framework.web.springjson.framework.config;

import java.io.Serializable;
import java.util.Collection;

import com.lzx.framework.config.BaseConfig;
import com.lzx.framework.utils.collection.IntHashMap;

public class PermissionConfig extends BaseConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    public static IntHashMap<BaseConfig> ALL = new IntHashMap<BaseConfig>();
    private String key;
    private String name;
    private int parent;
    private String url;
    private String privilege;
    private boolean reload;
    
    public static PermissionConfig getById(int id) {
	return (PermissionConfig) ALL.get(id);
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getParent() {
	return parent;
    }

    public void setParent(int parent) {
	this.parent = parent;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getPrivilege() {
	return privilege;
    }

    public void setPrivilege(String privilege) {
	this.privilege = privilege;
    }

    public static PermissionConfig getByKey(String name2) {
	Collection<BaseConfig> coll = ALL.values();
	for (BaseConfig b : coll) {
	    PermissionConfig p = (PermissionConfig) b;
	    if (p.getKey().equals(name2))
		return p;
	}
	return null;
    }

    public boolean isReload() {
        return reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

}
