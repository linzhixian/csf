package com.lzx.framework.web.springjson.framework;

import com.lzx.framework.rmi.RMIUtil;

public class Config {
	
	private String dcRmi;
	private String dataclasspath;
	
	public String getDcRmi() {
		return dcRmi;
	}

	public void setDcRmi(String dcRmi) {
		this.dcRmi = dcRmi;
		RMIUtil.RMI_URL = this.dcRmi;
	}

	public String getDataclasspath() {
	    return dataclasspath;
	}

	public void setDataclasspath(String dataclasspath) {
	    this.dataclasspath = dataclasspath;
	}


}
