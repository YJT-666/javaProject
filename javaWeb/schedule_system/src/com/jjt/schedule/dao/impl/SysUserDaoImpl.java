package com.jjt.schedule.dao.impl;

import com.jjt.schedule.dao.BaseDao;
import com.jjt.schedule.dao.SysUserDao;
import com.jjt.schedule.pojo.SysUser;

import java.util.List;

/**
 * ClassName: SysUserDaoImpl
 * Package: com.jjt.schedule.dao.impl
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 9:43
 * @Version 1.0
 */
public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT, ?, ?)";
        return baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid, username, user_pwd userPwd from sys_user where username=?";
        List<SysUser> sysUserList = baseQuery(SysUser.class, sql, username);
        if(sysUserList != null && sysUserList.size() > 0) {
            return sysUserList.get(0);
        }
        return null;
    }
}
