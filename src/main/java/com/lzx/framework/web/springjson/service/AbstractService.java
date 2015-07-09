/**
 * 
 */
package com.lzx.framework.web.springjson.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.FactoryBean;

import com.lzx.framework.rmi.RMIUtil;

/**
 * @author linzhixian 2014年10月24日下午2:27:04
 * 
 */
public abstract class AbstractService<T> implements FactoryBean<T> {

    protected Class<T> entityClass;

    public AbstractService() {
	Type genType = getClass().getGenericSuperclass();
	Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
	entityClass = ((Class<T>) params[0]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getObject() throws Exception {
	
	return (T) (RMIUtil.lookupRmi(entityClass));
    }

    @Override
    public Class<T> getObjectType() {
	return entityClass;
    }

    @Override
    public boolean isSingleton() {
	return true;
    }
}
