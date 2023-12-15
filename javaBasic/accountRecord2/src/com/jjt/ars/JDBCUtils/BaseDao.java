package com.jjt.ars.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * ClassName: BaseDao
 * Package: JDBCUtils
 * Description:
 *              抽象类，必须由具体数据表的DAO类继承后才可实例化对象
 *              Database access object 数据库访问对象
 *              基本上每一个数据表都应该有一个对应的DAO接口及其实现类
 *              发现对所有表的操作（增、删、改、查）代码重复度很高，
 *              所以可以抽取公共代码，给这些DAO的实现类可以抽取一个公共的父类，称为BaseDao
 *              其主要功能是：
 *                  1.获取数据库连接（使用JDBCUtils工具类）
 *                      JDBCUtils工具类当中获取数据库连接可以保证在同一个线程中先后获取的数据库连接对象是同一个对象
 *                  2.创建 preparedstatement 并且进行占位符赋值
 *                  3.发送SQL语句，并且获取结果
 *                  4.对于 查询 的话将查询的结果封装成一个 list<T> 返回
 *                  5.非事务数据库访问，进行数据库连接关闭。对于事务性质的数据库访问，数据库连接对象由服务对象管理。
 *              针对 DQL查询 可以调用 executeUpdate 方法
 *              非DQL 可以调用 executeQuery 方法
 * @Author jjt
 * @Create 2023/12/14 14:54
 * @Version 1.0
 */
public abstract class BaseDao {

    /**
     * 通用的执行数据的增、删、改的方法，适用插入一条数据
     *
     * @param sql  sql 带占位符的 SQL 语句
     * @param args the args  占位符的值，注意传入占位符的值的顺序必须等于SQL语句当中占位符?的顺序
     * @return the int   执行影响的行数
     * @throws SQLException the sql exception
     */
    protected int executeUpdate(String sql, Object... args) throws SQLException {
        // 1. 获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();

        // 2. 创建preparedstatement对象，对sql语句进行编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 对占位符进行赋值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]); // ?的编号从1开始，数组的下标从0开始
            }
        }

        // 4. 执行 sql 语句
        int rows = preparedStatement.executeUpdate();

        // 5. 关闭资源
        preparedStatement.close();
        if(connection.getAutoCommit()) {
            // 没有开启事务，因此回收数据库连接对象
            JDBCUtils.freeConnection();
        }
        // 返回影响的行数
        return rows;
    }


    /**
     * 通用的数据库查询多个 Javabean 对象方法，例如：多个员工对象，多个部门对象
     *
     * @param <T>   the type parameter     Javabean 类型
     * @param clazz the clazz  Javabean 类型的 class对象
     * @param sql   the sql    执行查询带占位符的 sql 语句
     * @param args  the args   占位符的值，注意传入占位符的值的顺序必须等于SQL语句当中占位符?的顺序
     * @return the array list   查询的结果返回 List<T> list
     */
    protected <T> ArrayList<T> executeQuery(Class<T> clazz, String sql, Object... args) throws Exception {

        ArrayList<T> arrayList = new ArrayList<>();  // 保存查询的结果

        // 1. 获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();

        // 2. 创建preparedstatement对象，对sql语句进行编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 对占位符进行赋值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]); // ?的编号从1开始，数组的下标从0开始
            }
        }
        // 4. 执行 sql 语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. 结果集解析
        /**
         *  metaData 是查询结果的元数据对象，包含查询结果有几列，列的名称是什么
         * */
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();  // 获取结果集的列数
        while (resultSet.next()) {
            // 遍历结果集ResultSet，把查询结果中的一条一条记录，变成一个一个T 对象，放到list中
            T t = clazz.newInstance();  // 要求这个类型必须有公共的无参构造
            for (int i = 1; i <= columnCount; i++) {
                // 把这条记录的每一个单元格的值取出来，设置到t对象对应的属性中
                Object object = resultSet.getObject(i); // 获得第i列的值对象
                String columnLabel = metaData.getColumnLabel(i);// 获取第i列的字段名或字段的别名
                /**
                 *  利用反射设置Javabean对象的属性值
                 * */
                Field declaredField = clazz.getDeclaredField(columnLabel);
                declaredField.setAccessible(true); // 这么做可以操作private属性
                declaredField.set(t, object);
            }
            arrayList.add(t);
        }

        // 6. 关闭资源
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()) {
            // 没有开启事务，因此回收数据库连接对象
            JDBCUtils.freeConnection();
        }

        // 返回查询的结果
        return arrayList;
    }

    /**
     * 通用的数据库查询一个 Javabean 对象方法
     *
     * @param <T>   the type parameter     Javabean 类型
     * @param clazz the clazz    Javabean 类型的 class对象
     * @param sql   the sql  执行查询带占位符的 sql 语句
     * @param args  the args  占位符的值，注意传入占位符的值的顺序必须等于SQL语句当中占位符?的顺序
     * @return the t     or   null 表示没有查询到数据
     * @throws Exception the exception
     */
    protected <T> T executeQueryBean(Class<T> clazz, String sql, Object... args) throws Exception {
        ArrayList<T> arrayList = executeQuery(clazz, sql, args);
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return arrayList.get(0);
    }
}
