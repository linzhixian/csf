package com.lzx.framework.web.springjson.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzx.framework.web.springjson.framework.daoentity.AdminUser;

public interface UserMapper {

    public List<AdminUser> selectListByParent(@Param("id") int id);

    public void delete(Integer id);

    public void insert(AdminUser user);

    public AdminUser selectOneByName(@Param("name") String name);
    
    public AdminUser selectOneByID(@Param("id") int id);

    public void update(AdminUser user);
    

    public AdminUser login(@Param("name") String name, @Param("password") String password);

    public void updatePassword(@Param("id") Integer id, @Param("password") String newpassword);

    public void updatePermission(Integer id, String gamename, String permission);

    public void updateMemo(Integer uid, String memo);
}
