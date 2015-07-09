package com.lzx.framework.rmi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {
	public static boolean isRepeat = true;//报异常是否重复调用
	//被代理的对象
	Object delegate;

	//构造函数，在构在这个代理处理器时，传入要被代理的对象
	public MyProxyHandler(Object delegate) {
		this.delegate = delegate;
	}

	//被代理的方法之前会调用的方法
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
//		System.out.println("我在被代理的方法之前执行");
		
		//被代理的方法开始执行
		int count = 60;
		Object object = null;
		try {
			object = method.invoke(delegate, args);
		} catch (Exception e) {
			e.printStackTrace();
			
			if(e instanceof InvocationTargetException){
				throw ((InvocationTargetException)e).getTargetException();
			}
			
			if(!isRepeat) {
				throw e;
			}

			while(count>0){
				Thread.sleep(3000);
				try {
					object = method.invoke(delegate, args);
				} catch (Exception e1) {
					count--;
					continue;
				}
				break;
			}
		}
//		System.out.println("Excute result:"+object);
		
//		System.out.println("我在被代理的方法之后执行");
		return object;

	}

}