package com.lzx.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;

public class MD5 {

    public static java.security.MessageDigest md;
    static {
	try {
	    md = java.security.MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
    }

    private final static byte[] HEX_DIGITAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String md5String(String value) {
	try {
	    byte[] t_bytes = md.digest(value.getBytes("utf-8")); // 锟斤拷锟絤d5
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
	}
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
	FileInputStream fis = new FileInputStream(file);
	try {
	    String md5 = DigestUtils.md5DigestAsHex(IOUtils.toByteArray(fis));
	    IOUtils.closeQuietly(fis);
	    return md5;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "ERROR";
    }

    public static String getMd5ByFile2(File file) throws FileNotFoundException {
	String value = null;
	FileInputStream in = new FileInputStream(file);
	try {
	    MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
	    MessageDigest md5 = MessageDigest.getInstance("MD5");
	    md5.update(byteBuffer);
	    BigInteger bi = new BigInteger(1, md5.digest());
	    value = bi.toString(16);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (null != in) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return value;
    }

    public static void main(String[] args) throws FileNotFoundException {
	File f = new File("pom.xml");
	System.out.println(getMd5ByFile(f));
	System.out.println(getMd5ByFile2(f));
    }
}
