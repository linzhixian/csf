/** 
 * ©2012 UFun Technology (HK) Limited Corporation. All rights reserved. 
 */
package com.lzx.framework.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2012-6-13 下午6:54:49
 * 
 */
public class StreamXmlApplicationContext extends AbstractXmlApplicationContext {

	private Resource[] configResources = null;

	public StreamXmlApplicationContext(InputStream configFileStream) {
		super();
		this.configResources = new Resource[] { new InputStreamResource(configFileStream) };
		this.refresh();
	}
	public StreamXmlApplicationContext(InputStream configFileStream,InputStream configFileStream2) {
		super();
		this.configResources = new Resource[] { new InputStreamResource(configFileStream) , new InputStreamResource(configFileStream2) };
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