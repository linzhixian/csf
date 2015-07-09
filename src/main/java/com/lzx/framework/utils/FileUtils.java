package com.lzx.framework.utils;

import java.io.File;

public class FileUtils {
    /**
     * 创建不存的目录
     * 
     * @param filePath 是目录或者文件
     * @author lzx 2015年5月30日上午10:47:25
     */
    public static void createDirs(String filePath) {
	int index = filePath.lastIndexOf(File.separator);
	int dotIndex = filePath.indexOf(".", index);
	File f = null;
	if (dotIndex > 0) {
	    // 如果是文件，把文件截掉
	    if (index > 0) {
		f = new File(filePath.substring(0, index));
	    } else {
		f = new File(filePath);
	    }

	} else {
	    f = new File(filePath);

	}
	if (!f.exists())
	    f.mkdirs();
    }
}
