
$.sessionTimeOutAlert = function() {
	$.messager.confirm('错误', "您离开太久，请重新登陆！",function(r){parent.location.reload();});		
};
$.checkQueryResultForLogin = function(result) {
	var code=result["code"];
	if(code!=null && code!=1 && code!=10) {
		if(code==9) {
			$.sessionTimeOutAlert();
			return true;
		} else{
			$.messager.alert('错误', code);
			return true;
		}
		
	}
	return false;
};
		
	
function getMyDefinedFormName(obj) {
	if (obj.parentNode == null)
		return null;
	if (obj.parentNode.nodeName.toLowerCase() == "form") {
		return obj.parentNode;
	} else {
		return getMyDefinedFormName(obj.parentNode);
	}
}

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
$.fn.serializeObjectWithDevicetypeAndGameid = function() {
	var o = this.serializeObject();
	o["devicetype"] = window.parent.global_devicetype;
	o["gameid"] = window.parent.global_gameid;
	return o;
};

$(document).ready(function() {
	$("#querySubmit").click($.onQueryClick);
	$("#querySubmit2").click($.onQueryClick);
});
$.submitQueryCallBack = function(result) {
	if($.checkQueryResultForLogin(result)) {
		return;
	}	
	update_page(result);
};

$.onQueryClick = function() {
	$.onSubmitClick(this,$.submitQueryCallBack ,true);
};
// sourceObj:必须是表单里的按钮；
$.onSubmitClick = function(sourceObj, callBackFunction,
		isWithDeviceTypeAndGameId,mtree,chk_value) {
	var obj = sourceObj;
	var domform = getMyDefinedFormName(obj);
	var myform = $(domform);
	if (domform != null && !myform.form('validate')) {
		return;
	}
	if (domform != null && !myValidate(myform)) {
		return;
	}
	var jsonuserinfo = {};
	if (domform != null) {
		if (isWithDeviceTypeAndGameId) {
			jsonuserinfo = myform.serializeObjectWithDevicetypeAndGameid();
		} else {
			jsonuserinfo = myform.serializeObject();
		}
		jsonuserinfo['permission']=mtree;
		if(chk_value!=null||chk_value!=""){
			jsonuserinfo['gamename']=chk_value;
		}
	}
	var jsonParams = {};
	jsonParams[sourceObj.name] = jsonuserinfo;
	// var jsonparams = JSON.stringify(jsonParams);
	//alert(JSON.stringify(jsonParams));
	$.submitQuery(jsonParams, callBackFunction);
};
//获得地址
function getControllerUrl() {
	//获取当前网址，如： http://localhost:8083/proj/meun.jsp  
	var curWwwPath = window.document.location.href;  
	//获取主机地址之后的目录，如： proj/meun.jsp  
	var pathName = window.document.location.pathname;  
	var pos = curWwwPath.indexOf(pathName);  
	//获取主机地址，如： http://localhost:8083  
	var localhostPath = curWwwPath.substring(0, pos);  
	//获取带"/"的项目名，如：/proj  
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
	return localhostPath+projectName+"/controller";
}
$.submitControl = function(jsonparams, callbackFunction) {
	$.ajax({
		url : getControllerUrl(), // 后台处理程序
		type : 'post', // 数据发送方式
		dataType : 'json', // 接受数据格式
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify(jsonparams), // 要传递的数据
		success : callbackFunction,
		error : errorCallbackFunction
	});
};
function errorCallbackFunction(XMLHttpRequest, info) {
	$.messager.alert('ajak错误', info);
}
$.submitQuery = function(jsonparams, update_page) {
	$.submitControl(jsonparams, update_page);
};
// 提交批量修改
$.submitBatchSave = function(name, update) {
	var rows = {};
	rows["rows"] = update;
	var jsonParams = {};
	jsonParams[name] = rows;
	// jsonParams = JSON.stringify(jsonParams);
	$.submitControl(jsonParams, saveResult);
};
function saveResult(saveResult) {
	var code = saveResult["code"];
	if (code == 1) {
		$.messager.alert("提示", "提交成功！");
		$("#datagrid").datagrid('acceptChanges');
	} else {
		$.messager.alert("提示", "提交失败！返回错误代码：" + code);
	}
};

function myValidate(myValidate) {
	return true;
}
$.deleteConfirm=function(deleteFunction) {
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	deleteFunction();    
	    }    
	});	
};

