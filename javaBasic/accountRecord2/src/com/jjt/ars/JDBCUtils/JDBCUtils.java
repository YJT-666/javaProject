package com.jjt.ars.JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCUtils
 * Package: JDBCUtils
 * Description:
 * JDBC 封装工具类，这个工具类的作用就是用来给所有的SQL操作提供“连接”，和释放连接
 * 1. 内部包含一个连接池对象，对外提供连接的方法和回收连接的方法！
 *         注意： 一个线程最多只能持有一个数据库连接对象，多次获取如果没有释放的话，获取的是同一个数据库连接对象
 * 2. 实现
 * 属性   dataSource  连接池对象[实例化一次]  采用静态代码块实现
 *                   连接池对象可以使得数据库连接得到重用、限制数据库服务器连接的上限等
 *         t1       线程本地变量，为同一个线程存储共享变量
 *                  存储了当前线程的数据库连接对象，方便后面事务时，同一个线程不同方法获取到同一个数据库连接对象
 *                  t1.get  可以获取ThreadLocal中当前线程共享变量的值
 *                  t1.set  可以设置ThreadLocal中当前线程共享变量的值
 *                  t1.remove 可以移除ThreadLocal中当前线程共享变量的值
 *
 * @Author jjt
 * @Create 2023 /12/14 13:57
 * @Version 1.0
 */
public class JDBCUtils {

    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();
    static {
        /*
        *   JdbcUtils类加载时，在静态代码块当中完成初始化数据库连接池对象。
        * */
        try {
            dataSource = getDruidDataSource();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对外提供数据库连接
     * @return the connection
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = t1.get();   // 获取当前线程保持的数据库连接对象
        if (connection == null) {
            // 当前线程还没有获取到数据连接对象，通过数据库连接池分配一个
            connection = dataSource.getConnection();
            t1.set(connection);
        }
        return connection;
    }

    /**
     * 回收数据库连接对象
     * @throws SQLException the sql exception
     */
    public static void freeConnection() throws SQLException {
        Connection connection = t1.get();
        if (connection != null) {
            // 当前线程持有数据库连接对象
            t1.remove();
            connection.setAutoCommit(true);  // 避免还给数据库连接池的连接不是自动提交模式
            connection.close();              // 回收连接，这里调用的是数据连接池连接对象的回收方法
        }
    }

    /**
     *  内部静态方法，根据 druid 数据库连接池配置参数文件，返回 druid数据库连接池对象
     *                1. 需要读取druid连接池对象的配置参数，必要参数包括
     *                      driverClassName=com.mysql.cj.jdbc.Driver   数据库驱动
     *                      username=root
     *                      password=root
     *                     url=jdbc:mysql://IP:port/databaseName
     *                2. 创建数据库连接池对象，会完成 数据驱动注册
     * */
    private static DataSource getDruidDataSource() throws Exception {
        Properties properties = new Properties();
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);
        return DruidDataSourceFactory.createDataSource(properties);
    }
}
