package com.jjt.schedule.service.impl;

import com.jjt.schedule.dao.SysUserDao;
import com.jjt.schedule.dao.impl.SysUserDaoImpl;
import com.jjt.schedule.pojo.SysUser;
import com.jjt.schedule.service.SysUserService;
import com.jjt.schedule.util.MD5Util;

/**
 * ClassName: SysUserServiceImpl
 * Package: com.jjt.schedule.service.impl
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 11:10
 * @Version 1.0
 */
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public int regist(SysUser sysUser) {
        // 1.需要将用户的明文密码转换为密码密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 2.将 sysUser信息存入数据库，调用 dao 层的方法
        return sysUserDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findUsername(String username) {
        return sysUserDao.findByUsername(username);
    }
}
