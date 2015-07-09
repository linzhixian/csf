package com.lzx.framework.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


public class StringUtil {
    private final static byte[] HEX_DIGITAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };


    private static String arrayToString(Object array) {
	int length = Array.getLength(array);
	int lastItem = length - 1;
	StringBuffer sb = new StringBuffer("[");
	Object obj;
	for (int i = 0; i < length; i++) {
	    obj = Array.get(array, i);
	    if (obj != null) {
		if (obj.getClass().isArray()) {
		    sb.append(arrayToString(obj));
		} else {
		    sb.append(beanToString(obj));
		}
	    } else {
		sb.append("[NULL]");
	    }
	    if (i < lastItem) {
		sb.append(", ");
	    }
	}
	sb.append("]");
	return sb.toString();
    }

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");

    private final static Class<?>[] primitiveClasses = new Class<?>[] { Boolean.class, Boolean.TYPE, Character.class, Character.TYPE, Byte.class, Byte.TYPE, Short.class,
	    Short.TYPE, Integer.class, Integer.TYPE, Long.class, Long.TYPE, Float.class, Float.TYPE, Double.class, Double.TYPE };

    public static boolean isPrimitiveClass(Class<?> cl) {
	for (Class<?> c : primitiveClasses) {
	    if (cl == c) {
		return true;
	    }
	}
	return false;
    }


    
    public static String beanToString(Object bean) {
	if (bean == null) {
	    return "null";
	}
	Class<?> clss = bean.getClass();

	StringBuffer text = new StringBuffer();
	text.append("{");
	text.append("\""+bean.getClass().getSimpleName()+"\":");
	text.append(" {");

	try {
	    BeanInfo beaninfo = Introspector.getBeanInfo(clss);
	    PropertyDescriptor[] pd = beaninfo.getPropertyDescriptors();
	    for (int i = 0; i < pd.length; i++) {
		if (pd[i] == null) {
		    continue;
		}
		Method method = pd[i].getReadMethod();
		if(method==null) {
		    System.out.println("is null");
		}
		Class<?> arg = method.getReturnType();
		if ("class".equals(pd[i].getName())) {
		    if (arg == Class.class) {
			continue;
		    }
		}
		Object val = method.invoke(bean);
		text.append(pd[i].getName());
		text.append(": ");
		text.append(objectToString(val));
		if (i != pd.length - 1)
		    text.append(", ");
	    }
	} catch (Exception e) {
	   // e.printStackTrace();
	    text.append(e.getMessage());
	}
	text.append("}");
	text.append("}");
	//System.out.println(bean.getClass().getName()+":"+text.toString());
	return text.toString();
    }


    public static String objectToString(Object object) {
	if (object == null)
	    return "null";
	if (object instanceof Map) {
	    object = ((Map<?, ?>) object).entrySet().toArray();
	} else if (object instanceof Collection) {
	    object = ((Collection<?>) object).toArray();
	}
	if (object.getClass().isArray())
	    return arrayToString(object);
	else {
	    Class<?> clss = object.getClass();
	    if (clss == String.class) {
		return object.toString();
	    }
	    if (isPrimitiveClass(clss)) {
		return object.toString();
	    }
	    if (Date.class.isAssignableFrom(clss)) {
		return format.format((Date) object);
	    }
	    if (Map.Entry.class.isAssignableFrom(clss)) {
		return "{key:" + objectToString(((Map.Entry) object).getKey()) + " value:" + objectToString(((Map.Entry) object).getValue()) + "}";
	    }
	    return beanToString(object);
	}
    }


    public static String md5String(String value) {
	java.security.MessageDigest md;
	try {
	    md = java.security.MessageDigest.getInstance("MD5");
	    byte[] t_bytes = md.digest(value.getBytes("utf-8")); // ���md5
	    int t_len = t_bytes.length;
	    byte[] t_new_bytes = new byte[2 * t_len];

	    for (int i = 0; i < t_len; i++) {
		t_new_bytes[i * 2] = HEX_DIGITAL[(t_bytes[i] & 0xF0) >> 4];
		t_new_bytes[i * 2 + 1] = HEX_DIGITAL[(t_bytes[i] & 0x0F)];
	    }
	    return new String(t_new_bytes, "utf-8");
	} catch (UnsupportedEncodingException e1) {
	    e1.printStackTrace();
	    return null;
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static int countWords(String str) {
	if (str == null || str.length() <= 0) {
	    return 0;
	}
	int len = 0;
	char c;
	for (int i = str.length() - 1; i >= 0; i--) {
	    c = str.charAt(i);
	    if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
		// 字母, 数字
		len++;
	    } else {
		if (Character.isLetter(c)) { // 中文
		    len += 2;
		} else { // 符号或控制字符
		    len++;
		}
	    }
	}
	return len;
    }

    /**
     * 返回百分比表示：四舍五入取整数
     * 
     * @param s
     * @return
     */
    public static String numToPer(float s) {
	String value = String.valueOf(Math.round(Math.round(s * 10000) / 100f));
	if (value.endsWith(".0"))
	    value = value.substring(0, value.length() - 2);
	return value + "%";
    }

    public static boolean isEmpty(String text) {
	return text == null || "".equals(text.trim());
    }

    public static void main(String[] args) {
	float s = 75 / 17148f;

	System.out.println(Math.round(s * 10000));
	System.out.println(Math.round(s * 10000) / 100f);
    }
}