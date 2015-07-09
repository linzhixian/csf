package com.lzx.framework.server;


import org.springframework.context.ApplicationContext;

public interface IServer {

	public String getName();
	
	public void initBeforeStart();
	public void start() throws Exception;
	public void initAfterStarted();
	
	public void beforeStop();
	public void stop() throws Exception ;
	public void afterStop();
	
	public void setApplicationContext(ApplicationContext applicationContext);
	public ApplicationContext getApplicationContext();
	
	
}
