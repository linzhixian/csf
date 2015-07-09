package com.lzx.framework.web.springjson.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.lzx.framework.web.springjson.beans.IRequestJson;
import com.lzx.framework.web.springjson.user.HttpUser;

public abstract class WebController extends CenterController {

    public Map<String, Object> controller(IRequestJson requestJson, HttpSession httpSession, HttpServletRequest request) {
	ApplicationContext webApplicationContext = (ApplicationContext) request.getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT");
	return super.controller(requestJson, httpSession, (HttpUser) httpSession.getAttribute("user"), webApplicationContext);
    }

}
