package com.lzx.framework.web.springjson.controller;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lzx.framework.web.springjson.beans.ResponseBase;
import com.lzx.framework.web.springjson.user.HttpUser;

public class CommandMethod {
    private Object obj;
    private Method method;
    private int methodType = 0;
    private boolean checkPermission;
    private boolean isCheckLogin;
    
    public boolean isCheckPermission() {
	return checkPermission;
    }

    public void setCheckPermission(boolean checkPermission) {
	this.checkPermission = checkPermission;
    }

    public CommandMethod(Object source, Method method, boolean checkPermission,boolean checkLogin) {
	this.obj = source;
	this.method = method;
	int paraCount = method.getParameterTypes().length;
	if (paraCount == 2) {
	    if (method.getParameterTypes()[1].getSimpleName().equals("HttpUser")) {
		methodType = 1;
	    } else if (method.getParameterTypes()[1].getSimpleName().equals("HttpSession")) {
		methodType = 3;
	    } else {
		methodType = 2;
	    }
	}

	this.checkPermission = checkPermission;
	this.isCheckLogin=checkLogin;
    }

    public Object call(Object arg, Map<String, String> header, HttpUser httpUser, HttpSession session) {
	ResponseBase rs = new ResponseBase();
	try {
	    @SuppressWarnings({ "rawtypes" })
	    Class c = arg.getClass();
	    if (methodType == 0) {
		return method.invoke(obj, c.cast(arg));
	    } else if (methodType == 1) {
		return method.invoke(obj, c.cast(arg), httpUser);
	    } else if (methodType == 3) {
		return method.invoke(obj, c.cast(arg), session);
	    }else if (methodType == 2) {
		return method.invoke(obj, c.cast(arg), header);
	    } else {
		rs.setCode(ResponseBase.FUNCTION_NOTFOUND);
		rs.setError("Exception:wrong paramters num");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    rs.setCode(ResponseBase.UNKONW_ERROR);
	    rs.setError("Exception:" + method.getName() + ":" + e.getMessage());
	}
	return rs;
    }

    public boolean isCheckLogin() {
        return isCheckLogin;
    }

    public void setCheckLogin(boolean isCheckLogin) {
        this.isCheckLogin = isCheckLogin;
    }
}
