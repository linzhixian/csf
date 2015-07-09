package com.lzx.framework.web.springjson.framework.jsonbean;

import java.util.List;

import com.lzx.framework.web.springjson.beans.ResponseBase;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;

public class ManagerUserQueryResult extends ResponseBase {
    private static final long serialVersionUID = 1L;

    private List<AdminUser> rows;
    private int total = 0;

    public List<AdminUser> getRows() {
	return rows;
    }

    public void setRows(List<AdminUser> rows) {
	this.rows = rows;
	if (this.rows != null)
	    this.total = this.rows.size();
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

}
