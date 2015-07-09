package com.lzx.framework.web.springjson.framework.config;

import java.io.File;
import java.io.IOException;

import com.lzx.framework.utils.file.CharSetConvertor;

public class RawFile {
    private String fileName; 
    private String content;
    private long lastModify;

    public RawFile(File f) throws IOException {
	byte[] cBytes=CharSetConvertor.readFileToTypes(f);
	this.content =CharSetConvertor.convertBytes(cBytes, f.getAbsolutePath()); ;
	this.fileName=f.getName();
	this.lastModify = f.lastModified();
    }



    public boolean isNew(long modify) {
	return lastModify != modify;
    }

    public String getFileName() {
        return fileName;
    }



    public String getContent() {
        return content;
    }



    public void setContent(String content) {
        this.content = content;
    }

}