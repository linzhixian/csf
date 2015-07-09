package com.lzx.framework.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可自定义命名线程的线程工厂
 * @project 33e9
 * @date 2011-9-9
 * @version 1.0
 * @company 33e9
 * @author zengweizhi
 * 
 * @review_history
 * [2011-9-9] create by zengweizhi
 */
public class NamingThreadFactory implements ThreadFactory {
	final ThreadGroup group;
	final AtomicInteger threadNumber = new AtomicInteger(1);
	final String namePrefix;
	final int priority;
	
	public NamingThreadFactory(String namePrefix){
		this(namePrefix,Thread.NORM_PRIORITY);
	}
	
	public NamingThreadFactory(String namePrefix,int priority){
		SecurityManager s = System.getSecurityManager();
		group = (s != null)? s.getThreadGroup() :
							 Thread.currentThread().getThreadGroup();
		this.namePrefix = namePrefix + "-";
		this.priority = priority;
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r,
							  namePrefix + threadNumber.getAndIncrement(),
							  0);
		if (t.isDaemon())
			t.setDaemon(false);
		t.setPriority(priority);
		return t;
	}
	
}

