package com.lzx.framework.web.springjson.framework.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.lzx.framework.config.BaseConfig;
import com.lzx.framework.utils.StringUtil;
import com.lzx.framework.web.springjson.annotation.WebAction;
import com.lzx.framework.web.springjson.annotation.WebCommand;
import com.lzx.framework.web.springjson.beans.ResponseBase;
import com.lzx.framework.web.springjson.framework.config.PermissionConfig;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;
import com.lzx.framework.web.springjson.framework.daoentity.DataGridResult;
import com.lzx.framework.web.springjson.framework.daoentity.UpdateAdminUser;
import com.lzx.framework.web.springjson.framework.jsonbean.ChangePwd;
import com.lzx.framework.web.springjson.framework.jsonbean.CheckLogin;
import com.lzx.framework.web.springjson.framework.jsonbean.ID;
import com.lzx.framework.web.springjson.framework.jsonbean.ManagerUserQueryResult;
import com.lzx.framework.web.springjson.framework.jsonbean.MenuTree;
import com.lzx.framework.web.springjson.framework.jsonbean.ResetPwd;
import com.lzx.framework.web.springjson.framework.mapper.UserMapper;
import com.lzx.framework.web.springjson.user.HttpUser;
import com.lzx.framework.web.springjson.user.Permission;

@WebCommand
@Service
@Lazy
public class ManagerCommands {

    @Autowired
    private UserMapper userMapper;

   

    public ManagerCommands() {
	System.out.println("----------");
    }
    @WebAction(name = "login",checkPermission=false,checkLogin=false)
    public ResponseBase login(AdminUser login,  HttpSession httpSession) {
	ResponseBase res=new ResponseBase();
	AdminUser user=userMapper.login(login.getName(), login.getPassword());
	if(user!=null) {
	    res.setCode(ResponseBase.SUCCESS);
	    HttpUser myuser=new HttpUser();
	    myuser.setAttachmentObject(user);
	    Permission ps=new Permission();
	    ps.setType(user.getType());
	    myuser.setPermission(ps);
	    myuser.loadPermission(user.queryPermissionArray());
	    httpSession.setAttribute("user", myuser);
	    myuser.setHttpsession(httpSession);
	    httpSession.setAttribute("user", myuser);
	} else {
	    res.setCode(ResponseBase.LOGIN_FAIL);
	}
	return res;
    }



    /**
     * userid 需要修改的权利用户id，-1表示无
     * httpUser 谁在修改
     * 被修改的权限范围不能超过修改人
     * @author lzx 2015年4月18日上午10:56:56
     */
    @WebAction(name = "getAddORupdatePermissionTree")
    public DataGridResult getAddORupdatePermissionTree(ID userid, HttpUser httpUser) {
	 AdminUser modifyUser=null;
	 List<String> modifyUserPermission=null;
	if(userid.getId()!=-1) {
	    modifyUser = userMapper.selectOneByID(userid.getId());
            modifyUserPermission = Arrays.asList(modifyUser.queryPermissionArray());
            if(!isOwner((AdminUser)httpUser.getAttachmentObject(),userid.getId())) return null;
	}
	DataGridResult r = new DataGridResult();
	List<MenuTree> list = new ArrayList<MenuTree>();
	
	AdminUser aUser = (AdminUser) httpUser.getAttachmentObject();
	List<String> ownerPermission = Arrays.asList(aUser.queryPermissionArray());
	  List<Integer> parentsnodeList=getAllparentnode();
	Iterator<BaseConfig> iter=PermissionConfig.ALL.values().iterator();
	while(iter.hasNext()) {
	    PermissionConfig pc = (PermissionConfig)iter.next();
	    MenuTree mTree = new MenuTree();
	    if (aUser.isRoot() || ownerPermission.contains(pc.getKey())) {
		mTree.setNodeid(pc.getId());
		mTree.setId(pc.getKey());
		mTree.setText(pc.getName());
		mTree.setState("open");
		mTree.setParent(pc.getParent());
		if (modifyUser != null ) {
		    if (modifyUserPermission.contains(pc.getKey())) {
			mTree.setChecked("true");
			if (parentsnodeList.contains(pc.getId())) {
			    mTree.setChecked(null);
			}
		    }
		}
		list.add(mTree);
		mTree = new MenuTree();
	    }
	}
	List<Map<String, Object>>  list2=createEasyUiTree(list, -1);
	r = new DataGridResult(list2.size(), list2);
	return r;
    }
    //检查childUserid是否是owner创建的用户
    private boolean isOwner(AdminUser owner,int uid) {
	if(owner.isRoot()) return true;//root用户可以操纵任何用户
	AdminUser u=userMapper.selectOneByID(uid);
	return u.getCreateid()==owner.getId();
    }
    public List<Integer> getAllparentnode(){
	List<Integer> parentsnodeList=new ArrayList<Integer>();
	
	Iterator<BaseConfig> iter=PermissionConfig.ALL.values().iterator();
	while(iter.hasNext()) {
	    PermissionConfig pc = (PermissionConfig)iter.next();
	    if(!parentsnodeList.contains(pc.getParent())){
		parentsnodeList.add(pc.getParent());
	    }
	}
	return parentsnodeList;
    }
    private List<Map<String, Object>> createEasyUiTree(List<MenuTree> list, int pId) {
	List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
	for (int i = 0; i < list.size(); i++) {
	    Map<String, Object> map = null;
	    MenuTree tt = (MenuTree) list.get(i);
	    if (tt.getParent() == pId) {
		map = new HashMap<String, Object>();
		map.put("id", list.get(i).getId());
		map.put("text", list.get(i).getText());
		map.put("state", list.get(i).getState());
		map.put("children", createEasyUiTree(list, tt.getNodeid()));
		if (list.get(i).getChecked() != null && list.get(i).getChecked().equals("true")) {
		    map.put("checked", list.get(i).getChecked());
		}
	    }
	    if (map != null)
		parentList.add(map);
	}
	return parentList;
    }
    @WebAction(name = "queryAdminUser",checkPermission=true)
    public ManagerUserQueryResult queryAdminUser(Object request, HttpUser httpUser) {
	AdminUser aUser = (AdminUser) httpUser.getAttachmentObject();
	List<AdminUser> list = userMapper.selectListByParent(aUser.getId());
	ManagerUserQueryResult r = new ManagerUserQueryResult();
	r.setRows(list);
	r.setCode(ResponseBase.SUCCESS);
	return r;
    }

    @WebAction(name = "deleteAdminUser",checkPermission=true)
    public ResponseBase deleteAdminUser(ID request, HttpUser httpUser) {
	AdminUser owner = (AdminUser) httpUser.getAttachmentObject();
	//权限控制，只能删除子用户	
	if(!this.isOwner(owner, request.getId())) return null;
	ResponseBase b = new ResponseBase();
	userMapper.delete(request.getId());
	deleteChildAdminUser(request.getId());
	b.setCode(ResponseBase.SUCCESS);
	return b;
    }
    //删子用户和子用户的子用户
    private void deleteChildAdminUser(Integer uid) {
	List<AdminUser> childlist=userMapper.selectListByParent(uid);
	for(AdminUser u:childlist) {
	    userMapper.delete(u.getId());
	    deleteChildAdminUser(u.getId());
	}
    }
    @WebAction(name = "addAdminUser",checkPermission=true)
    public ResponseBase addAdminUser(AdminUser user, HttpUser httpUser) {
	ResponseBase b = new ResponseBase();
	AdminUser aUser = (AdminUser) httpUser.getAttachmentObject();
	try {
	    if (!StringUtil.isEmpty(user.getName()) && !StringUtil.isEmpty(user.getName())) {
		if (userMapper.selectOneByName(user.getName()) == null) {
		    if (aUser.getType() == 0) {
			user.setType(1);
		    } else {
			user.setType(2);
		    }
		    user.setCreateid(aUser.getId());
		    userMapper.insert(user);
		    b.setCode(ResponseBase.SUCCESS);
		} else {
		    b.setCode(ResponseBase.EXIST);
		}

	    } else {
		b.setCode(ResponseBase.WRONG_PARAM);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    b.setCode(ResponseBase.WRONG_PARAM);
	}
	return b;
    }
    @WebAction(name = "updateAdminUser",checkPermission=true)
    public ResponseBase updateAdminUser(UpdateAdminUser user, HttpUser httpUser) {  
	AdminUser owner = (AdminUser) httpUser.getAttachmentObject();
	//权限控制，只能操纵子用户	
	if(!this.isOwner(owner, user.getId())) return null;
	ResponseBase b = new ResponseBase();
	//检查权限是否在父权限范围内:以防止被黑
	if(PermissionUtil.isPermissionInParent(owner,user.getGamename(),user.getPermission())) {
	    b.setCode(ResponseBase.WRONG_PARAM);
	    return b;
	}
	
	updatePermission(user.getId(),user.getGamename(),user.getPermission(),user.getMemo());
	b.setCode(ResponseBase.SUCCESS);
	return b;

    }
    //更新用户权限：同时必须更新子用户及其所有子子用户的权限
    private void updatePermission(Integer uid,String gamename,String permission,String memo) {
	updatePermission(uid,gamename,permission);
	userMapper.updateMemo(uid, memo);
    }
    private void updatePermission(Integer uid,String gamename,String permission) {
 	AdminUser user=userMapper.selectOneByID(uid);
 	//如果原来有系统用户管理的权限，现在没有了，则必须删除所有其子目录
 	boolean isDeleteChildUser=user.getPermission().contains("adminuser") && !permission.contains("adminuser");
 	userMapper.updatePermission(uid, gamename, permission);
 	if(isDeleteChildUser) {
 	  this.deleteChildAdminUser(uid);  
 	} else {	
 	  updateChildUserPermission(uid, gamename, permission);
 	}
     }
    private void updateChildUserPermission(Integer uid,String gamename,String permission) {
	List<AdminUser> childlist=userMapper.selectListByParent(uid);
	for(AdminUser u:childlist) {
	   if(!PermissionUtil.isIn(gamename, u.getGamename()) || !PermissionUtil.isIn(permission, u.getPermission())) {
	       updatePermission(u.getId(),PermissionUtil.filterNoIN(gamename,u.getGamename()),PermissionUtil.filterNoIN(permission,u.getPermission()));
	   }
	}
    }
    @WebAction(name = "exitLogin")
    public ResponseBase exitLogin(AdminUser user, HttpUser httpUser) {
	ResponseBase b = new ResponseBase();
	HttpSession httpSession=(HttpSession) httpUser.getHttpsession();
	httpSession.removeAttribute("user");
	b.setCode(ResponseBase.SUCCESS);
	return b;
    }
    
    @WebAction(name = "changePwd")
    public ResponseBase ChangePwd(ChangePwd user, HttpUser httpUser) {
	ResponseBase res=new ResponseBase();
	AdminUser aUser = (AdminUser) httpUser.getAttachmentObject();
	if(aUser.getPassword().equals(user.getPassword())) {
	  userMapper.updatePassword(aUser.getId(),user.getNewpassword());
	  res.setCode(ResponseBase.SUCCESS);
	  System.out.println("修改密码成功，"+user.getNewpassword());
	} else {
	    res.setCode(ResponseBase.WRONG_PARAM);
	    System.out.println("修改密码失败");
	}
	return res;
    }

    
    @WebAction(name = "resetPwd")
    public ResponseBase resetPwd(ResetPwd resetPwd, HttpUser httpUser) {
	ResponseBase res=new ResponseBase();
	AdminUser aUser = (AdminUser) httpUser.getAttachmentObject();
	if(!this.isOwner(aUser, resetPwd.getId())) return null;
	  userMapper.updatePassword(resetPwd.getId(),resetPwd.getNewpassword());
	  res.setCode(ResponseBase.SUCCESS);
	  System.out.println("修改密码成功，"+resetPwd.getId()+":"+resetPwd.getNewpassword());

	return res;
    }
    

}
