package com.lzx.framework.web.springjson.framework.jsonbean;

import java.io.Serializable;

public class GameCombobox implements Serializable {
    private int id;
    private String value;
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
