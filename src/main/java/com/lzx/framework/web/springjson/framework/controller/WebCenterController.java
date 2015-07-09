package com.lzx.framework.web.springjson.framework.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lzx.framework.config.BaseConfig;
import com.lzx.framework.web.springjson.beans.ResponseBase;
import com.lzx.framework.web.springjson.controller.CenterController;
import com.lzx.framework.web.springjson.framework.RequestJson;
import com.lzx.framework.web.springjson.framework.command.ManagerCommands;
import com.lzx.framework.web.springjson.framework.config.PermissionConfig;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;
import com.lzx.framework.web.springjson.user.HttpUser;

public class WebCenterController extends CenterController  {

    @Autowired
    private ManagerCommands managerCommands;

/*    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody*/

    public Map<String, Object> controller(RequestJson requestJson, HttpSession httpSession,HttpServletRequest request) {
	if (requestJson.getCheckLogin() != null) {
	    return checkLogin(httpSession);
	}

	ApplicationContext webApplicationContext=(ApplicationContext)request.getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT");
	return super.controller(requestJson,httpSession,(HttpUser)httpSession.getAttribute("user"),webApplicationContext);
    }

    protected Map<String, Object> checkLogin(HttpSession httpSession) {
	Map<String, Object> output = new HashMap<String, Object>();
	AdminUser user=null;
	if ((HttpUser)httpSession.getAttribute("user")!=null) {
	    user=(AdminUser) ((HttpUser)httpSession.getAttribute("user")).getAttachmentObject();
	}else {
	    output.put("code", ResponseBase.NO_LOGIN);
	}
	if (user == null) {
	    output.put("code", ResponseBase.NO_LOGIN);
	} else {
	    output.put("code", ResponseBase.SUCCESS);
	    output.put("type", user.getType());
	    output.put("menuContent", geUserMenuContent(user));
	    output.put("name",user.getName());
	}
	return output;
    }
    public List<PermissionConfig> geUserMenuContent(AdminUser user){
	List<PermissionConfig> list=new ArrayList<PermissionConfig>();
	Iterator<Integer> iter=PermissionConfig.ALL.keySet().iterator();
	List<Integer> keylist=new ArrayList<Integer>();
	while(iter.hasNext()) {
	    keylist.add(iter.next());
	   
	}
	Collections.sort(keylist);
	for(Integer id:keylist) {
	    PermissionConfig pc = (PermissionConfig)PermissionConfig.ALL.get(id);
	     if(user.isRoot()|| user.getPermission().contains(pc.getKey())){
	      list.add(pc);
	    }
	}
	return list;
    }
    @Override
    protected boolean checkSign(Integer uid, String sign, String source) {
	return false;
    }

    @Override
    protected Class getRequestJsonClass() {
	return RequestJson.class;
    }

}
