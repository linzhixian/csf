package com.lzx.framework.web.springjson.framework.command;

import java.util.Iterator;

import net.sf.json.JSONArray;

import com.lzx.framework.utils.StringUtil;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;

public class PermissionUtil {
    
    /**
     * 检查gamename,permission是否在owner范围内
     * @author lzx 2015年4月29日上午10:30:07
     */
    public static boolean isPermissionInParent(AdminUser owner,String gamename,String permission) {
	if(StringUtil.isEmpty(gamename) && StringUtil.isEmpty(permission)) return true;
	if(!isIn(owner.getGamename(),gamename)) return false;
	if(!isIn(owner.getPermission(),permission)) return false;
	return true;
    }
    public static String[] jsonArrayToArray(String str) {
	JSONArray json=JSONArray.fromObject(str);
	String[] array=new String[json.size()];
	json.toArray(array);
	return array;
    }
    public static boolean isIn(String owner,String child) {
	if(StringUtil.isEmpty(child)) return true;
	String[] ga=jsonArrayToArray(child);
	for(String gn:ga){
	    if(!owner.contains(gn)) {
		return false;
	    }
	}
	return true;
    }
    /**
     * 过滤掉child中有，而owner中没有的项
     * @author lzx 2015年4月29日上午10:53:00
     */
    public static String filterNoIN(String owner, String child) {
	if(isIn(owner,child)) return child;
	String[] array=jsonArrayToArray(child);
	JSONArray json=JSONArray.fromObject(child);
	for(String item:array) {
	    if(!owner.contains(item)) {
		json.remove(item);
	    }
	}
	return json.toString();
    }
}
