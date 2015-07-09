package com.lzx.framework.server.rmi;

import java.io.IOException;
import java.rmi.RemoteException;

import com.lzx.framework.rmi.RMIService;
import com.lzx.framework.server.AbstractServer;

/**
 * RmiSerever约定： 1）spring 打开自动加载bean
 * 2）通过查找Spring中加载的bean有类名结尾为Service,且继承一个接口类名也是Service结尾
 * 
 * @author lzx
 *
 */
public class RmiServer extends AbstractServer {

    @Override
    public void start() {
	System.out.println("----start rmi server");
	RMIService rmiService = new RMIService();
	try {
	    rmiService.startup();
	    // 自动注册rmi服务
	    String[] beanNames = this.getApplicationContext().getBeanDefinitionNames();
	    for (String name : beanNames) {
		if (name.endsWith("_RMI")) {
		    System.out.println("----findRMi:" + name);
		    Object beanService = this.getApplicationContext().getBean(name);
		    Class[] interfaces = beanService.getClass().getInterfaces();
		    if (interfaces.length >= 1) {
			for (int j = 0; j < interfaces.length; j++) {
			    if (interfaces[j].getName().endsWith("Service")) {
				rmiService.bindRmi(interfaces[j], beanService);
				System.out.println("----bindRmi:" + name);
			    }
			}
		    } else {
			System.out.println("----nobindRmi:" + name + ":interfaces:" + interfaces.length);
		    }
		}
	    }
	} catch (RemoteException e) {
	    e.printStackTrace();
	    System.exit(0);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

    @Override
    public void stop() {

    }

}
