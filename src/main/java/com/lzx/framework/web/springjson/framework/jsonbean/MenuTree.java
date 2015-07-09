package com.lzx.framework.web.springjson.framework.jsonbean;

import java.io.Serializable;

public class MenuTree implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String text;
    private String state;
    private String checked;
    private String children;
    private int parent;
    private int nodeid;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getNodeid() {
        return nodeid;
    }
    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getChecked() {
        return checked;
    }
    public void setChecked(String checked) {
        this.checked = checked;
    }
    public String getChildren() {
        return children;
    }
    public void setChildren(String children) {
        this.children = children;
    }
    public int getParent() {
        return parent;
    }
    public void setParent(int parent) {
        this.parent = parent;
    }
    
}
