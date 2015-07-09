package com.lzx.framework.rmi;

import java.util.HashMap;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class RMIUtil {
	
	public static String RMI_URL="rmi://127.0.0.1:"+RMIService.port+"/";

	private static HashMap<String,Object> rmisMap=new HashMap<String,Object>();
	
	public static Object lookupRmi(Class rmiInterfaceClasss) {
		String url=RMI_URL+rmiInterfaceClasss.getName();
		return lookupRmi(rmiInterfaceClasss,url);
	}
	public static Object lookupRmi(Class rmiInterfaceClasss,String url) {
		if(rmisMap.get(url)==null) {
			rmisMap.put(url, createRmiService(rmiInterfaceClasss,url));
		}
		System.out.println("url:"+url);
		Object object = rmisMap.get(url);
/*		MyProxyHandler mph = new MyProxyHandler(object);
		return Proxy.newProxyInstance(object.getClass().getClassLoader()   
                , object.getClass().getInterfaces(), mph);*/
		return object;
	}
	
	public static Object createRmiService(Class rmiInterface,String url) {
		System.out.println("createRmiService:"+url+",class:"+rmiInterface);
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(rmiInterface);
		bean.setServiceUrl(url);
		bean.setRefreshStubOnConnectFailure(true);
		bean.setLookupStubOnStartup(true);
		bean.afterPropertiesSet();
		return  bean.getObject();
	}


}
