package com.lzx.framework.web.springjson.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.framework.web.springjson.annotation.WebAction;
import com.lzx.framework.web.springjson.annotation.WebActionIngore;
import com.lzx.framework.web.springjson.annotation.WebActionNeedSign;
import com.lzx.framework.web.springjson.annotation.WebCommand;
import com.lzx.framework.web.springjson.beans.IRequestJson;
import com.lzx.framework.web.springjson.beans.IUidSupport;
import com.lzx.framework.web.springjson.beans.ResponseBase;
import com.lzx.framework.web.springjson.user.HttpUser;

public abstract class CenterController {

    private Map<String, CommandMethod> commandMap;

    boolean isInit = false;

    protected Map<String, Object> controller(IRequestJson requestJson) {
	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	return controller(requestJson, null, webApplicationContext);
    }
    protected Map<String, Object> controller(IRequestJson requestJson,HttpUser httpUser) {
	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	return controller(requestJson, httpUser, webApplicationContext);
    }
    ReentrantLock lock=new ReentrantLock();
    protected Map<String, Object> controller(IRequestJson requestJson, HttpUser httpUser, ApplicationContext webApplicationContext) {
	
	return controller(requestJson,null,httpUser,webApplicationContext);
    }
    protected Map<String, Object> controller(IRequestJson requestJson,HttpSession session, HttpUser httpUser, ApplicationContext webApplicationContext) {
	if (!isInit) {
	    lock.lock();
	    if(!isInit) {
	     this.init(webApplicationContext);
	    }
	    lock.unlock();
	}
	Map<String, Object> output = new HashMap<>();
	Integer uid = requestJson.getUid();
	// 判断签名
	if (requestJson.getSign() != null) {
	    if (checkSign(uid, requestJson.getSign(), requestJson.getSource())) {
		requestJson.getValue().setUid(uid);
		ObjectMapper mapper = new ObjectMapper();
		try {
		    requestJson = mapper.readValue(requestJson.getSource(), getRequestJsonClass());
		} catch (JsonParseException e) {
		    e.printStackTrace();
		} catch (JsonMappingException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		executeMethod(requestJson, uid, output, true, httpUser,session);
		return output;
	    } else {
		output.put("code", 5);
		return output;
	    }
	}
	executeMethod(requestJson, uid, output, false, httpUser,session);
	return output;
    }

    protected abstract Class getRequestJsonClass();

    private boolean hasPermission(HttpUser httpUser, String name) {
	CommandMethod comandMethod = commandMap.get(name);
	if (comandMethod != null && comandMethod.isCheckPermission()) {
	    if (httpUser != null)
		return httpUser.hasPermission(name);
	    else
		return false;
	} else {
	    return true;
	}

    }
    private boolean checkLogin(HttpUser httpUser, String name) {
	CommandMethod comandMethod = commandMap.get(name);
	if (comandMethod != null && comandMethod.isCheckLogin()) {
	    if (httpUser != null)
		return true;		
	    else
		return false;
	} else {
	    return true;
	}

    }
    private void executeMethod(IRequestJson requestJson, Integer uid, Map<String, Object> output, boolean isSign, HttpUser httpUser,HttpSession session) {
	Method[] methods = requestJson.getClass().getMethods();// 获取类中的方法
	// 用户提交过来的requestJson里面有不为空的对象就把对象派发到知道的处理类
	for (Method m : methods) {
	    String methodName = m.getName();
	    WebActionIngore ass = m.getAnnotation(WebActionIngore.class);
	    // CheckRight checkRight = m.getAnnotation(CheckRight.class);
	    if (ass != null)
		continue;

	    if (methodName.startsWith("get") && !methodName.equals("getClass")) {
		String name = toLowerCaseFirstOne(methodName.substring(3));
		try {
		    Object obj = m.invoke(requestJson);// 掉方法返回对象
		    if (obj != null) {
			if (!hasPermission(httpUser, name)) {
			    output.put("code", ResponseBase.NO_PERMISSION);
			    return;
			}
			if (!checkLogin(httpUser, name)) {
			    output.put("code", ResponseBase.NO_LOGIN);
			    return;
			}
			WebActionNeedSign signAnno = m.getAnnotation(WebActionNeedSign.class);
			if (signAnno != null && !isSign) {
			    output.put("code", 5);
			    return;
			}

			if (obj instanceof IUidSupport) {
			    if (uid != null)
				((IUidSupport) obj).setUid(uid);
			}
			Object result = callAction(name, obj, requestJson.getHeader(), httpUser,session);
			output.put(name + "Response", result);
			if (result instanceof ResponseBase) {
			    output.put("code", ((ResponseBase) result).getCode());
			    output.put("error", ((ResponseBase) result).getError());
			}
		    }
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		    e.printStackTrace();
		    ResponseBase rs = new ResponseBase(ResponseBase.UNKONW_ERROR);
		    rs.setError("Exception:" + name + ":" + e.getMessage());
		    output.put(name + "Response", rs);
		}
	    }

	}
    }

    protected abstract boolean checkSign(Integer uid, String sign, String source);

    private Object callAction(String name, Object obj, Map<String, String> header, HttpUser httpUser,HttpSession session) {
	CommandMethod m = commandMap.get(name);
	System.err.println("callMethod:" + name );
	if (m == null) {
	    System.err.println("callMethod:" + name + " is null");
	    ResponseBase rs = new ResponseBase(ResponseBase.FUNCTION_NOTFOUND);
	    rs.setError("Exception:" + name + ":method not found.");
	    return rs;
	}

	return m.call(obj, header, httpUser,session);
    }

    /**
     * 搜索spring容器里所有带WebCommand 注解的对象，并把带WebAction注解的方法注册到map里，以便之后可以快速调用
     * 
     * @author lzx 2014年11月21日下午2:37:51
     */
    protected void init(ApplicationContext context) {
	commandMap = new HashMap<String, CommandMethod>();
	Map<String, Object> commandsMap = context.getBeansWithAnnotation(WebCommand.class);
	System.out.println("-----init CenterController");
	Iterator<Object> iter = commandsMap.values().iterator();
	if(!iter.hasNext()) {
	    System.out.println("-----init CenterController,but fund nothing about WebCommand");
	}
	while (iter.hasNext()) {
	    Object command = iter.next();
	    Method[] method = command.getClass().getDeclaredMethods();
	    for (Method m : method) {
		WebAction waction = m.getAnnotation(WebAction.class);
		if (waction != null) {
		    String name = waction.name();
		    boolean checkPermission = waction.checkPermission();
		    CommandMethod cm = new CommandMethod(command, m, checkPermission,waction.checkLogin());
		    if(commandMap.put(name, cm)!=null) {
			throw new RuntimeException("WebAction:"+name+" duplicate definition");
		    }
		    System.err.println("-----------load webcommand:" + name);
		}
	    }

	}
	this.isInit = true;
    }

    public static String toLowerCaseFirstOne(String s) {
	if (Character.isLowerCase(s.charAt(0))) // 确定指定的字符是否为小写字母
	    return s;
	else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
