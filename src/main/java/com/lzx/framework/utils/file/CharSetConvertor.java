package com.lzx.framework.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

/**
 * 自动判断文件的字符集,并正确读取
 * 
 * @author java_1
 * 
 */
public class CharSetConvertor {

    public static String readFile(File file) {
	if (!file.exists() || file.isDirectory()) {
	    System.out.println("File not found! " + file.getAbsolutePath());
	    return null;
	}

	return new FileContentReader().readFile(file);
    }

    public static byte[] readFileToTypes(File file) {
	if (!file.exists() || file.isDirectory()) {
	    System.out.println("File not found! " + file.getAbsolutePath());
	    return null;
	}

	return new FileContentReader().readFileToBytes(file);
    }

    public static String readFile(String file) {
	return readFile(new File(file));
    }

    public static String convertBytes(byte[] bytes, String fileName) {
	return new ByteArrayReader().readBytes(bytes, fileName);
    }

    private static class ByteArrayReader implements nsICharsetDetectionObserver {

	private String charset;

	@Override
	public void Notify(String cs) {
	    this.charset = cs;
	}

	public String readBytes(byte[] bytes, String fileName) {

	    nsDetector detector = new nsDetector(nsDetector.CHINESE);
	    detector.Init(this);

	    detector.DoIt(bytes, bytes.length, false);
	    detector.DataEnd();

	    if (charset == null || charset.equals("null")) {
		System.err.println("无法识别文件编码, 使用默认的utf8解码. 如有乱码, 请手动转换为utf8: " + fileName);
	    } else if (charset.equals("Big5")) {
		System.err.println("识别出文件内含有繁体字, 可能导致乱码. 手动转为utf8: " + fileName);
	    }
	    try {
		return new String(bytes, charset == null ? "utf8" : charset);
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	    }
	}

    }

    private static class FileContentReader implements nsICharsetDetectionObserver {

	private String charset;

	@Override
	public void Notify(String cs) {
	    this.charset = cs;
	}

	public String readFile(File file) {
	    assert file != null && file.isFile();
	    nsDetector detector = new nsDetector(nsDetector.CHINESE);
	    detector.Init(this);

	    try {
		FileInputStream fileinputstream = new FileInputStream(file);
		byte[] abyte0 = new byte[fileinputstream.available()];
		fileinputstream.read(abyte0);
		detector.DoIt(abyte0, abyte0.length, false);
		detector.DataEnd();
		fileinputstream.close();
		if (charset == null || charset.equals("null")) {
		    System.err.println("无法识别文件编码, 使用默认的utf8解码. 如有乱码, 请手动转换为utf8: " + file.getAbsolutePath());
		} else if (charset.equals("Big5")) {
		    System.err.println("识别出文件内含有繁体字, 可能导致乱码. 手动转为utf8: " + file.getAbsolutePath());
		}
		// return new String(abyte0, charset == null ? "utf8" :
		// charset);\
		String content = new String(abyte0);
		System.out.println(file.getName() + ":size=" + file.length() + ":" + abyte0.length + ":content=" + content.length());
		return content;
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return null;
	}

	public byte[] readFileToBytes(File file) {
	    assert file != null && file.isFile();
	    nsDetector detector = new nsDetector(nsDetector.CHINESE);
	    detector.Init(this);

	    try {
		FileInputStream fileinputstream = new FileInputStream(file);
		byte[] abyte0 = new byte[fileinputstream.available()];
		fileinputstream.read(abyte0);

		fileinputstream.close();

		return abyte0;
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return null;
	}

    }
}
