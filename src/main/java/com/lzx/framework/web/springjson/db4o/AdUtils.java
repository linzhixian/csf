package com.lzx.framework.web.springjson.db4o;

import javax.servlet.ServletContext;

public class AdUtils {

    public static String getDataDirAbsolutePath(ServletContext servletContext) {
	if(getDataDir(servletContext)==null) return null;
	return servletContext.getRealPath(getDataDir(servletContext));
	
    }

    public static String getDataDir(ServletContext servletContext) {
	return servletContext.getInitParameter("data-home-dir");
	
    }
}
