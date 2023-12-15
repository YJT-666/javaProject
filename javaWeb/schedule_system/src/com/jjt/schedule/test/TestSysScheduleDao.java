package com.jjt.schedule.test;

import com.jjt.schedule.dao.SysScheduleDao;
import com.jjt.schedule.dao.impl.SysScheduleDaoImpl;
import com.jjt.schedule.pojo.SysSchedule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * ClassName: TestSysScheduleDao
 * Package: com.jjt.schedule.test
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 10:56
 * @Version 1.0
 */
public class TestSysScheduleDao {
    private static SysScheduleDao sysScheduleDao;

    @BeforeClass
    public static void initSysScheduleDao() {
        sysScheduleDao = new SysScheduleDaoImpl();
    }

    @Test
    public void testAddSchedule() {
        int rows = sysScheduleDao.addSchedule(new SysSchedule(
                null, 2, "学习数据库", 1));
        System.out.println("rows: " + rows);
    }
    @Test
    public void testFindAll() {
        List<SysSchedule> all = sysScheduleDao.findAll();
        all.forEach(System.out::println);
    }
}
