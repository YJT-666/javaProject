package com.jjt.schedule.test;

import com.jjt.schedule.dao.BaseDao;
import com.jjt.schedule.pojo.SysUser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.Socket;
import java.util.List;

/**
 * ClassName: TestBaseDao
 * Package: com.jjt.schedule.test
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 10:25
 * @Version 1.0
 */
public class TestBaseDao {
    
    private static BaseDao baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDao();
    }

    @Test
    public void testBaseQueryObject() {
        String sql = "select count(1) from sys_user";
        Long count = baseDao.baseQueryObject(Long.class, sql);
        System.out.println(count);
    }

    @Test
    public void testBaseQuery(){
        String sql = "select uid,username,user_pwd userPwd from sys_user";
        List<SysUser> sysUserList = baseDao.baseQuery(SysUser.class, sql);
        sysUserList.forEach(System.out::println);
    }

    @Test
    public void testBaseUpdate(){
        String sql = "insert into sys_schedule values(DEFAULT, ?, ?, ?)";
        int rows = baseDao.baseUpdate(sql, 1, "学习JAVA", 0);
        System.out.println("rows: " + rows);
    }
}
