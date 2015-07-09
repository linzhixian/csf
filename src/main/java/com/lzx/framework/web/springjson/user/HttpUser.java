package com.lzx.framework.web.springjson.user;

import javax.servlet.http.HttpSession;

import com.lzx.framework.web.springjson.framework.config.PermissionConfig;

public class HttpUser {
    private Permission permission;

    private Object attachmentObject;

    private HttpSession httpsession;

    public Object getAttachmentObject() {
	return attachmentObject;
    }

    public void setAttachmentObject(Object attachmentObject) {
	this.attachmentObject = attachmentObject;
    }

    public Permission getPermission() {
	return permission;
    }

    public void setPermission(Permission permission) {
	this.permission = permission;
    }

    public boolean hasPermission(String name) {

	if (permission == null)
	    return false;
	return permission.hasPermission(name);
    }

    public void loadPermission(String[] permissionArray) {
	for (String name : permissionArray) {
	    this.permission.add(name);
	    PermissionConfig config = PermissionConfig.getByKey(name);
	    if (config != null) {
		String privilege = config.getPrivilege();
		if (privilege != null) {
		    String[] ss = privilege.split(",");
		    for (String s : ss) {
			this.permission.add(s);
		    }
		}
	    }
	}
    }

    public void setType(int type) {
	permission.setType(type);
    }

    public HttpSession getHttpsession() {
	return httpsession;
    }

    public void setHttpsession(HttpSession httpsession) {
	this.httpsession = httpsession;
    }

}
