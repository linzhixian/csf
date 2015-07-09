package com.lzx.framework.web.springjson.framework;

import com.lzx.framework.web.springjson.beans.AbstractRequestJson;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;
import com.lzx.framework.web.springjson.framework.daoentity.UpdateAdminUser;
import com.lzx.framework.web.springjson.framework.jsonbean.ChangePwd;
import com.lzx.framework.web.springjson.framework.jsonbean.CheckLogin;
import com.lzx.framework.web.springjson.framework.jsonbean.ID;
import com.lzx.framework.web.springjson.framework.jsonbean.ResetPwd;

public class RequestJson extends AbstractRequestJson {

    private Object queryAdminUser;

    private ID deleteAdminUser;

    private AdminUser addAdminUser;

    private UpdateAdminUser updateAdminUser;

    private ID getAddORupdatePermissionTree;

    private AdminUser exitLogin;

    private ChangePwd changePwd;

    private CheckLogin checkLogin;

    private AdminUser login;

    private Integer cutoverGeme;

    private AdminUser showMenu;

    private AdminUser showMenuUrl;

    private ResetPwd resetPwd;
   

    public ID getDeleteAdminUser() {
	return deleteAdminUser;
    }

    public void setDeleteAdminUser(ID deleteAdminUser) {
	this.deleteAdminUser = deleteAdminUser;
    }

    public AdminUser getAddAdminUser() {
	return addAdminUser;
    }

    public void setAddAdminUser(AdminUser addAdminUser) {
	this.addAdminUser = addAdminUser;
    }

    public UpdateAdminUser getUpdateAdminUser() {
	return updateAdminUser;
    }

    public void setUpdateAdminUser(UpdateAdminUser updateAdminUser) {
	this.updateAdminUser = updateAdminUser;
    }

    public CheckLogin getCheckLogin() {
	return checkLogin;
    }

    public void setCheckLogin(CheckLogin checkLogin) {
	this.checkLogin = checkLogin;
    }

    public AdminUser getLogin() {
	return login;
    }

    public void setLogin(AdminUser login) {
	this.login = login;
    }

    public AdminUser getExitLogin() {
	return exitLogin;
    }

    public void setExitLogin(AdminUser exitLogin) {
	this.exitLogin = exitLogin;
    }

    public Integer getCutoverGeme() {
	return cutoverGeme;
    }

    public void setCutoverGeme(Integer cutoverGeme) {
	this.cutoverGeme = cutoverGeme;
    }

    public AdminUser getShowMenu() {
	return showMenu;
    }

    public void setShowMenu(AdminUser showMenu) {
	this.showMenu = showMenu;
    }

    public AdminUser getShowMenuUrl() {
	return showMenuUrl;
    }

    public void setShowMenuUrl(AdminUser showMenuUrl) {
	this.showMenuUrl = showMenuUrl;
    }

    public ID getGetAddORupdatePermissionTree() {
	return getAddORupdatePermissionTree;
    }

    public void setGetAddORupdatePermissionTree(ID getAddORupdatePermissionTree) {
	this.getAddORupdatePermissionTree = getAddORupdatePermissionTree;
    }

    public ChangePwd getChangePwd() {
	return changePwd;
    }

    public void setChangePwd(ChangePwd changePwd) {
	this.changePwd = changePwd;
    }

    public ResetPwd getResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(ResetPwd resetPwd) {
        this.resetPwd = resetPwd;
    }

    public Object getQueryAdminUser() {
        return queryAdminUser;
    }

    public void setQueryAdminUser(Object queryAdminUser) {
        this.queryAdminUser = queryAdminUser;
    }

}
