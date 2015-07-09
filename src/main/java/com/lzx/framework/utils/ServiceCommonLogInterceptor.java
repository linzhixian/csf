package com.lzx.framework.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通用方法调用日志拦截器, 常用于服务器开发. 它使用 commons-logging 框架把方法的调用记入日志，它的记录分为两个级别：
 * <li>DEBUG级别纪录每次方法的调用，调用的参数，返回值，以及异常的调用栈</li>
 * <li>WARN级别纪录每次方法的异常</li>
 * <p>
 * 主要方法：无
 * </p>
 * <p>
 * 典型使用场景：本类一般不被直接使用，而是在applicationContext.xml文件中配置。有几个主要选项:
 * </p>
 * <li>logArguments: boolean 是否记录调用参数，默认为false</li>
 * <li>logReturnValue: boolean 是否记录返回值，默认为true</li>
 * <li>logExceptionStack: boolean 是否记录异常调用栈，默认为true</li>
 * 
 * @author lt
 * @version $Id: ServiceCommonLogInterceptor.java 7 2004-09-15 07:12:21Z lt $
 */
public class ServiceCommonLogInterceptor implements MethodInterceptor {
    private boolean logArguments = false;

    private boolean logReturnValue = false;

    private boolean logExceptionStack = true;
    
    private boolean logGetMethod = true;

    /**
     * 是否记录方法参数
     * 
     * @return Returns the logArguments.
     */
    public boolean isLogArguments() {
        return logArguments;
    }

    /**
     * @param logArguments
     *            The logArguments to set.
     */
    public void setLogArguments(boolean logArguments) {
        this.logArguments = logArguments;
    }

    /**
     * 是否记录异常调用栈
     * 
     * @return Returns the logExceptionStack.
     */
    public boolean isLogExceptionStack() {
        return logExceptionStack;
    }

    /**
     * @param logExceptionStack
     *            The logExceptionStack to set.
     */
    public void setLogExceptionStack(boolean logExceptionStack) {
        this.logExceptionStack = logExceptionStack;
    }

    /**
     * 是否记录返回值
     * 
     * @return Returns the logReturnValue.
     */
    public boolean isLogReturnValue() {
        return logReturnValue;
    }

    /**
     * @param logReturnValue
     *            The logReturnValue to set.
     */
    public void setLogReturnValue(boolean logReturnValue) {
        this.logReturnValue = logReturnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Log logger = LogFactory.getLog(invocation.getMethod()
                .getDeclaringClass());
        StringBuffer summary = new StringBuffer();
        long startTime = System.currentTimeMillis();
        printInvocationName(summary, invocation);
        try {
            if (logger.isDebugEnabled() && logArguments)
                printInvocationArguments(summary, invocation);
            Object retValue = invocation.proceed();
            if (logger.isDebugEnabled() && logReturnValue) {
                printReturnValue(summary, retValue);
                printCost(summary, System.currentTimeMillis() - startTime);
                //如果禁止记录get ,那么不记所有get方法调用
                if (!(!this.logGetMethod  && invocation.getMethod().getName().startsWith("get")) ) {
                	logger.debug(summary);
                }
            }
            return retValue;
        } catch (Throwable e) {
            if (logger.isDebugEnabled() && logExceptionStack)
                printException(summary, e);
            
            printCost(summary, System.currentTimeMillis() - startTime);
            
            if (e instanceof Error)
                logger.warn(summary);
            else if (logger.isDebugEnabled())
                logger.debug(summary);
            throw e;
        }
    }

    /**
     * @param e
     */
    private void printException(StringBuffer summary, Throwable e) {
        summary.append(" Throws Exception: [");
        summary.append(e);
        StackTraceElement[] traces = e.getStackTrace();
        for (int i = 0; i < traces.length; ++i) {
            summary.append(traces[i].getClassName()).append("@").append(
                    traces[i].getFileName()).append(":").append(
                    traces[i].getLineNumber());
            if (i != traces.length - 1)
                summary.append(", ");
        }
        summary.append("]");
    }

    /**
     * @param invocation
     */
    private void printInvocationName(StringBuffer summary,
            MethodInvocation invocation) {
        summary.append(invocation.getMethod().getDeclaringClass().getName())
                .append(".").append(invocation.getMethod().getName());
    }

    /**
     * @param retValue
     */
    private void printReturnValue(StringBuffer summary, Object retValue) {
        summary.append(" Returns: ")
                .append(StringUtil.objectToString(retValue));
    }

    /**
     * @param invocation
     * @return
     */
    private void printInvocationArguments(StringBuffer summary,
            MethodInvocation invocation) {
        summary.append(" Arguments:").append(
                StringUtil.objectToString(invocation.getArguments()));
    }
    
    private void printCost(StringBuffer summary, long time) {
        summary.append("cost : " + time);
    }

    /**
     * 是否记录get方法，缺省值为true
     * @return
     */
	public boolean isLogGetMethod() {
		return logGetMethod;
	}

	public void setLogGetMethod(boolean logGetMethod) {
		this.logGetMethod = logGetMethod;
	}
}