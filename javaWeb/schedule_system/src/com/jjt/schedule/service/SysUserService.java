package com.jjt.schedule.service;

import com.jjt.schedule.pojo.SysUser;

/**
 * ClassName: SysUserService
 * Package: com.jjt.schedule.service
 * Description:
 *              该接口定义了以 sys_user表格为核心的业务处理功能
 * @Author jjt
 * @Create 2023/12/15 11:08
 * @Version 1.0
 */
public interface SysUserService {

    /**
     * 注册用户的信息
     *
     * @param sysUser 要注册的用户名和密码，以sysUser对象的形式接受
     * @return the int 注册成功返回>0，注册失败返回0
     */
    int regist(SysUser sysUser);

    /**
     * 根据用户名获得用户信息的完整方法
     *
     * @param username the username  要查询的用户名
     * @return the sys user    如果找到了返回sysUser对象
     *                          找不到返回null
     */
    SysUser findUsername(String username);
}
