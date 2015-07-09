package com.lzx.framework.web.springjson.converter;

import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.JavaType;
import com.lzx.framework.web.springjson.beans.IRequestJson;

public class MyJsonConverter extends MappingJackson2HttpMessageConverter {
    protected static Logger logger = LoggerFactory.getLogger(MyJsonConverter.class);
    
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

	JavaType javaType = getJavaType(clazz);
	try {
	    StringBuffer buf=new StringBuffer();
	    InputStream stream=inputMessage.getBody();
	    int read=0;
	    while((read=stream.read())>0) {
		buf.append((char)read);
		
	    }
	    String body=buf.toString();
	    body = new String(body.getBytes("ISO8859-1"),"utf-8");
	    logger.debug("IN:"+body);
	  //  System.out.println("IN:"+body);
	    Object jsonObj=this.getObjectMapper().readValue(body,javaType);
	    JSONObject jsonObj2 = JSONObject.fromObject(body);
	    body=jsonObj2.getJSONObject("value").toString();
	    ((IRequestJson)jsonObj).setSource(body);
	   // return this.getObjectMapper().readValue(inputMessage.getBody(), javaType);
	    return jsonObj;
	} catch (IOException ex) {
	    ex.printStackTrace();
	    throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
	}
    }
    
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
		throws IOException, HttpMessageNotWritableException {
	//System.out.println("OUT:"+this.getObjectMapper().writeValueAsString(object));
	logger.debug("OUT:"+this.getObjectMapper().writeValueAsString(object));
	super.writeInternal(object, outputMessage);
    }
}
