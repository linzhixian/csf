package com.lzx.framework.web.springjson.user;

import java.util.HashSet;
import java.util.Set;

public class Permission {
    public static final int TYPE_ROOT = 0;

    private int type;

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }

    private Set<String> all = new HashSet<String>();

    public boolean hasPermission(String name) {
	if (type == TYPE_ROOT)
	    return true;
	return all.contains(name);
    }

    public void add(String name) {
	all.add(name);
    }

}
