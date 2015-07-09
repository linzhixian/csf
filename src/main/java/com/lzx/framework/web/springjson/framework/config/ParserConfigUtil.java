package com.lzx.framework.web.springjson.framework.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzx.framework.config.BaseConfig;
import com.lzx.framework.utils.collection.IntHashMap;

public class ParserConfigUtil {
    protected static Logger logger = LoggerFactory.getLogger(ConfigService.class);

    public static void parserConfigTxt(RawFile configFile,String loadBeanPath) {

	String as[] = org.apache.commons.lang.StringUtils.splitByWholeSeparatorPreserveAllTokens(configFile.getContent(), "\r\n");
	if(as.length<2) {
	    System.out.println(configFile.getFileName());
	    
	}
	String heads[] = org.apache.commons.lang.StringUtils.splitByWholeSeparatorPreserveAllTokens(as[1], "\t");
	try {
	    String className = firstUpperCase(getFileShortName(configFile.getFileName()));
	    className = loadBeanPath + "."+className+"Config";
	    Class configClass = null;
	    try {
		configClass = Class.forName(className);
	    } catch (ClassNotFoundException e) {
		logger.warn("can't find class [" + className + "] for configFile:" + configFile.getFileName());
		System.err.println("can't find class [" + className + "] for configFile:" + configFile.getFileName());
		return;
	    }
	    IntHashMap<BaseConfig> all = new IntHashMap<BaseConfig>(100);
	    Field[] temp = configClass.getDeclaredFields();
	    Field[] temp2 = configClass.getSuperclass().getDeclaredFields();
	    Map<String, Field> fieldMap = new HashMap<String, Field>();
	    addToMap(temp, fieldMap);
	    addToMap(temp2, fieldMap);
	    if (as.length > 2) {
		for (int j = 2; j < as.length; j++) {
		    String data = as[j].trim();
		    // System.out.println("parser:" + data);

		    String[] values = org.apache.commons.lang.StringUtils.splitByWholeSeparatorPreserveAllTokens(data, "\t");
		    if (values.length == 0)
			continue;
		    try {
			Object instance = configClass.newInstance();

			for (int i = 0; i < values.length; i++) {
			    String fieldName = removeDoubleQuote(heads[i].trim()).trim();
			    Class oType = null;
			    try {
				oType = PropertyUtils.getPropertyType(instance, fieldName);
				PropertyUtils.setSimpleProperty(instance, fieldName, ConvertUtils.convert(removeDoubleQuote(values[i]), oType));
			    } catch (Throwable e) {
				System.err
					.println("Error:When setSimpleProperty:Instacne=" + instance + ",Field=" + fieldName + ",value=" + values[i] + ",type=" + oType);
				throw new IllegalArgumentException(e);
			    }
			}
			BaseConfig t = ((BaseConfig) instance);
			BaseConfig r = all.put(t.getId(), t);
			if (r != null) {
			    System.err.println("读取配置文件错误: " + configFile.getFileName() + ": id 重复 " + t.getId());
			    throw new IllegalArgumentException();
			}
		    } catch (InstantiationException | IllegalAccessException e) {
			System.err.println("读取配置文件错误: " + configFile.getFileName());
			throw new IllegalArgumentException(e);
		    }
		}
	    }
	    Field[] map = configClass.getFields();
	    for (Field f : map) {
		if (f.getName().equals("ALL") && Modifier.isStatic(f.getModifiers())) {
		    f.set(null, all);
		    break;
		}
	    }
	} catch (IllegalArgumentException | IllegalAccessException e) {
	    System.err.println("读取配置文件错误: " + configFile.getFileName());
	    throw new IllegalArgumentException(e);
	}
    }

    /**
     * 去除头尾双引号
     * 
     * @param str
     * @return
     */
    public static String removeDoubleQuote(String str) {
	if (str == null)
	    return str;
	int headerLength = str.length();
	if (headerLength > 2) {
	    if (str.charAt(0) == '"' && str.charAt(headerLength - 1) == '"')
		return str.substring(1, headerLength - 1);
	}
	return str;
    }

    private static void addToMap(Field[] temp, Map<String, Field> fieldMap) {
	for (Field f : temp) {
	    if (!Modifier.isStatic(f.getModifiers())) {
		fieldMap.put(f.getName(), f);
	    }
	}
    }

    /**
     * 去除文件的扩展名
     * 
     * @param fileName
     * @return
     */
    public static String getFileShortName(String fileName) {
	int pos = fileName.indexOf(".");
	if (pos > 0) {
	    return fileName.substring(0, pos);
	}
	return fileName;
    }

    /**
     * 首字母大写
     * 
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
	return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
