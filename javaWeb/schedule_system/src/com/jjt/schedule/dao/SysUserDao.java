package com.jjt.schedule.dao;

import com.jjt.schedule.pojo.SysUser;

/**
 * ClassName: SysUserDao
 * Package: com.jjt.schedule.dao
 * Description:
 *              Data access Object 数据访问对象
 *              该类用于定义针对表格的CURD方法
 *              针对每一个表格，应该有一个相应的 dao 对象
 *              dao 层一般需要定义接口(用于规范)和实现类
 *                  接口用于规范方法，使得调用者不必关心 DAO 类怎么去实现的
 *                  调用者可以只关注这个方法能做什么
 *                  实现类，就是一种去实现该接口的方法
 *
 * @Author jjt
 * @Create 2023/12/15 9:35
 * @Version 1.0
 */
public interface SysUserDao {

    /**
     * 向数据库当中增加一条用户记录的方法
     *
     * @param sysUser   用户记录实体对象
     * @return the int   >0 增加成功
     *                   =0 增加失败
     */
    int addSysUser(SysUser sysUser);

    /**
     * 通过用户名查找用户记录
     *
     * @param username the username   用户名
     * @return the sys user     找到返回包含该用户信息的 sysUser 对象
     *                          找不到返回null
     */
    SysUser findByUsername(String username);
}
