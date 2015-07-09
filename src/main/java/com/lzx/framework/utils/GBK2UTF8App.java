package com.lzx.framework.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
public class GBK2UTF8App {

	/**
	 * 将制定目录下的所有Java源文件的编码格式从GBK修改为UTF-8
	 */
	public static void main(String[] args) throws Exception {
		//GBK编码格式源码路径
		String srcDirPath = "E:\\work\\workspace\\lol-testClients\\src\\";
		
		//获取所有java文件
		gbkToUtf8_DIR(new File(srcDirPath));

	}
	public static void gbkToUtf8_DIR(File file) throws IOException {
		System.out.println("ConvertDir:"+file.getAbsolutePath());
		Collection<File> javaGbkFileCol =  FileUtils.listFiles(file, new String[]{"java","txt","xml","proto"}, true);
		for (File javaGbkFile : javaGbkFileCol) {
			if(javaGbkFile.isDirectory()) {
				gbkToUtf8_DIR(javaGbkFile);
			} else {
				gbkToUtf8(javaGbkFile);
			}
		}
		System.out.println("GBK to UTF8 Success!");
	}
	public static void gbkToUtf8(File file) throws IOException {
		//使用GBK读取数据，然后用UTF-8写入数据
		System.out.println("Convert:"+file.getAbsolutePath());
		java.io.StringWriter bw= new java.io.StringWriter();
		FileUtils.writeLines(new File(file.getAbsolutePath()+".utf8"), "UTF-8", FileUtils.readLines(file, "GBK"));	
		new File(file.getAbsolutePath()).delete();
		FileUtils.copyFile(new File(file.getAbsolutePath()+".utf8"), new File(file.getAbsolutePath()));
		new File(file.getAbsolutePath()+".utf8").delete();
	}

}
