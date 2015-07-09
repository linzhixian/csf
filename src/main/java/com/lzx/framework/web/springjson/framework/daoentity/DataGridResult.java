package com.lzx.framework.web.springjson.framework.daoentity;

import com.lzx.framework.web.springjson.beans.ResponseBase;

public class DataGridResult extends ResponseBase {
    private int total;
    private Object rows;

    public DataGridResult() {
	super();
    }

    public DataGridResult(Object rows) {
	super();
	this.rows = rows;
    }

    public DataGridResult(int total, Object rows) {
	super();
	this.total = total;
	this.rows = rows;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public Object getRows() {
	return rows;
    }

    public void setRows(Object rows) {
	this.rows = rows;
    }

    public String toString() {
	return "total" + total + "\t" + "rows" + rows;
    }
}
