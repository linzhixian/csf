
package com.lzx.framework.server;

import org.springframework.context.ApplicationContext;

public abstract class AbstractServer implements IServer {
	
	protected ApplicationContext applicationContext;
	private int id;
	protected String name;
	
	protected String appRootDir="./";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	@Override
	public void initBeforeStart() {
		
	}
	@Override
	public void initAfterStarted() {
		
	}
	@Override
	public void beforeStop() {
		
	}
	@Override
	public void afterStop() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppRootDir() {
		return appRootDir;
	}

	public void setAppRootDir(String appRootDir) {
		this.appRootDir = appRootDir;
	}
}
