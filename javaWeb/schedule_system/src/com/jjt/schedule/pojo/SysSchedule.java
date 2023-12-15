package com.jjt.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: SysSchedule
 * Package: com.jjt.schedule.pojo
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/14 18:10
 * @Version 1.0
 */
@AllArgsConstructor  // 添加了全参构造器
@NoArgsConstructor   // 添加了无参构造器
@Data    // 增加了 getter setter equals hashcode  toString 方法
public class SysSchedule implements Serializable {
    private Integer sid;
    private Integer uid;
    private String title;
    private  Integer completed;
}
