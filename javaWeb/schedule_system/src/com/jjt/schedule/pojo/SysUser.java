package com.jjt.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * ClassName: SysUser
 * Package: com.jjt.schedule.pojo
 * Description:
 *              1. 实体类的类名和表格名称应该对应（对应不是一致）
 *              2. 实体类的属性名和表格当中列名应该对应
 *              3. 每一个属性都必须是私有的
 *              4. 每一个数据应该都具备getter 和 setter 方法
 *              5. 必须具备无参构造器
 *              6. 应该实现序列化接口 (缓存、分布式项目数据传递可能会将对象序列化）
 *              7. 应该重写类的 hashcode 和 equals 方法
 *              8. toString 可重写也可不重写
 *
 *   使用 lombok 帮助我们生成 getter setter 全参构造  无参构造  equals hashcode  toString
 *   lombok使用步骤
 *      1. 安装lombok
 *      2. 勾选 enable annotation processer
 * @Author jjt
 * @Create 2023/12/14 17:46
 * @Version 1.0
 */
@AllArgsConstructor  // 添加了全参构造器
@NoArgsConstructor   // 添加了无参构造器
@Data    // 增加了 getter setter equals hashcode  toString 方法
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
}
