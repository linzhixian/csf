package com.lzx.framework.web.springjson.framework;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lzx.framework.web.springjson.db4o.Db4oSource;


public class CloseDb4oContextListener implements ServletContextListener {


	public CloseDb4oContextListener() {
	}


	public void contextInitialized(ServletContextEvent arg0) {
	}


	public void contextDestroyed(ServletContextEvent sc) {
		//关闭线程池，否则有可能导致tomcat不能正常关闭，下次启动不起来、
		System.out.println("----------------------contextDestroyed");
		Db4oSource.close();
	}

}