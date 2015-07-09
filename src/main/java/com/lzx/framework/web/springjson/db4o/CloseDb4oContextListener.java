package com.lzx.framework.web.springjson.db4o;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CloseDb4oContextListener implements ServletContextListener {

    public CloseDb4oContextListener() {
    }

    public void contextInitialized(ServletContextEvent arg0) {
	String datahomedir = AdUtils.getDataDirAbsolutePath(arg0.getServletContext());
	if (datahomedir == null) {
	    // throw new RuntimeException("no data-home-dir");
	} else {
	    File f = new File(datahomedir);
	    if (!f.exists())
		f.mkdirs();
	    Db4oSource.DB_URL = datahomedir + File.separator + "db.db4o";
	}

    }

    public void contextDestroyed(ServletContextEvent sc) {
	// 关闭线程池，否则有可能导致tomcat不能正常关闭，下次启动不起来、
	System.out.println("----------------------contextDestroyed");
	Db4oSource.close();
    }

}