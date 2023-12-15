package com.jjt.schedule.dao;

import com.jjt.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * ClassName: SysScheduleDao
 * Package: com.jjt.schedule.dao
 * Description:
 *
 * @Author jjt
 * @Create 2023 /12/15 9:36
 * @Version 1.0
 */
public interface SysScheduleDao {

    /**
     *  用于向数据库中添加一条日程记录
     *
     * @param schedule  日程数据以SysSchedule 实体类对象形式入参
     * @return 返回影响数据库记录的行数
     *          行数为0说明增加失败
     *          行数大于0说明增加成功
     */
    int addSchedule(SysSchedule schedule);

    /**
     * 查询 所有用户的所有日程
     * @return the list   将所有日程放入到一个 List<SysSchedule> 容器当中
     */
    List<SysSchedule> findAll();
}
