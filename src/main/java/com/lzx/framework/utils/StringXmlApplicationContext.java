/** 
 * ©2012 UFun Technology (HK) Limited Corporation. All rights reserved. 
 */
package com.lzx.framework.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

/**
 * @Description:通过字符串导入Spring配置文件
 * @author lzx
 * @date 2012-6-13 下午6:54:49
 * 
 */
public class StringXmlApplicationContext extends AbstractXmlApplicationContext {

	private Resource[] configResources = null;

	public StringXmlApplicationContext(String str) {
		super();
		this.configResources = new Resource[] { new ByteArrayResource(str.getBytes()) };
		this.refresh();
	}
	public StringXmlApplicationContext(String str1,String str2,String str3) throws UnsupportedEncodingException {
		super();
		this.configResources = new Resource[] {  new ByteArrayResource(str1.getBytes("UTF-8"))  ,  new ByteArrayResource(str2.getBytes("UTF-8")) , new ByteArrayResource(str3.getBytes("UTF-8"))  };
		this.refresh();
	}
	public StringXmlApplicationContext(String[] strs) throws UnsupportedEncodingException {
		super();
		this.configResources = new Resource[strs.length];
		for(int i=0;i<strs.length;i++) {
			this.configResources[i]=new ByteArrayResource(strs[i].getBytes("UTF-8")) ;
		}
		this.refresh();
	}
	@Override
	protected Resource[] getConfigResources() {
		return this.configResources;
	}

	@Override
	public void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		super.loadBeanDefinitions(reader);
	}
}