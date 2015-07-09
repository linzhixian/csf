package com.lzx.framework.web.springjson.beans;

import java.io.Serializable;

public class ResponseBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;

    private String error;

    public final static int SUCCESS = 1;
    public final static int NO_PERMISSION = 2; // 没权限
    public final static int FUNCTION_NOTFOUND = 3; // 对应方法找不到
    public final static int WRONG_PARAM = 4; // 参数错误
    public final static int UNKONW_ERROR = 5;// 未知错误
    public final static int NO_EXIST = 6;// 操作对象不存在
    public final static int EXIST = 7;// 操作对象已存在
    public final static int SEAL = 8; // 封停账号
    public final static int NO_LOGIN = 9; // 封停账号
    public final static int LOGIN_FAIL = 10; // 登陆失败

    public final static ResponseBase SUCCESS_OBJ = new ResponseBase(ResponseBase.SUCCESS);
    public final static ResponseBase ERROR_OBJ = new ResponseBase(ResponseBase.UNKONW_ERROR);
    public static final ResponseBase WRONG_PARAM_OBJ = new ResponseBase(ResponseBase.WRONG_PARAM);
    public static final ResponseBase EXIST_OBJ = new ResponseBase(ResponseBase.EXIST,"对象已存在");;

    public ResponseBase(Integer code) {
	this.code = code;
    }

    public ResponseBase(Integer code, String error) {
	this.code = code;
	this.error = error;
    }

    public ResponseBase() {
    }

    public Integer getCode() {
	return code;
    }

    public void setCode(Integer code) {
	this.code = code;
    }

    public String getError() {
	return error;
    }

    public void setError(String error) {
	this.error = error;
    }
}
