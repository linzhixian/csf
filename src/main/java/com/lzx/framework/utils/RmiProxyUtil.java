package com.lzx.framework.utils;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;


public class RmiProxyUtil {
    public static <T> T getRemoteProxy(Class<T> clazz, String url) {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(clazz);
        proxy.setServiceUrl(url);
        proxy.setLookupStubOnStartup(true);
        proxy.setRefreshStubOnConnectFailure(true);
        proxy.setCacheStub(true);
        proxy.afterPropertiesSet();
        return (T) proxy.getObject();
    }
}
