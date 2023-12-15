package com.jjt.schedule.dao.impl;

import com.jjt.schedule.dao.BaseDao;
import com.jjt.schedule.dao.SysScheduleDao;
import com.jjt.schedule.pojo.SysSchedule;

import java.sql.SQLClientInfoException;
import java.util.List;

/**
 * ClassName: SysScheduleDaoImpl
 * Package: com.jjt.schedule.dao.impl
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 9:48
 * @Version 1.0
 */
public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    /**
     * @param schedule
     * @return
     */
    @Override
    public int addSchedule(SysSchedule schedule) {
        String sql = "insert into sys_schedule values(DEFAULT, ?, ?, ?)";
        return baseUpdate(sql, schedule.getUid(), schedule.getTitle(),
                schedule.getCompleted());
    }

    /**
     * @return
     */
    @Override
    public List<SysSchedule> findAll() {
        String sql = "select sid,uid,title,completed from sys_schedule";
        return baseQuery(SysSchedule.class, sql);
    }
}
