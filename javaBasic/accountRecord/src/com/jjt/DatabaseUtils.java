package com.jjt;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * ClassName: DatabaseUtils
 * Package: jjt
 * Description:
 *                  与数据库相关的工具类
 * @Author jjt
 * @Create 2023/12/13 13:39
 * @Version 1.0
 */
public class DatabaseUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 连接数据库
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountRecord",
                "root", "123456");
        return connection;
    }

    /*
    *   查询一条最新记录
    * */
    public static RecordItem queryLastItem() throws SQLException, ClassNotFoundException {
        RecordItem recordItem = new RecordItem();


        // 1. 获取数据库连接
        Connection connection = getConnection();

        // 2.创建preparedStatement
        String sql = "SELECT * \n" +
                "FROM `accountUse1`\n" +
                "WHERE `id`=(\n" +
                "    SELECT MAX(`id`) \n" +
                "    FROM `accountUse1`\n" +
                ")\n" +
                ";";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 4. 取出查询结果
        if (resultSet.next()) {
            recordItem.setId((int) resultSet.getObject("id"));
            recordItem.setBalance((double) resultSet.getObject("balance"));
            recordItem.setLevel(RecordItem.str2level( (String)resultSet.getObject("level") ));
            recordItem.setCash((double) resultSet.getObject("cash"));
            recordItem.setComment((String) resultSet.getObject("comment"));
            recordItem.setDateTime(((LocalDateTime) resultSet.getObject("dateTime")).format(formatter));
            if (recordItem.getBalance() != RecordItem.getBalanceLive()) {
                RecordItem.setBalanceLive(recordItem.getBalance());  // 修正当前的金额
            }
        }

        // 5. 关闭资源
        preparedStatement.close();
        resultSet.close();
        connection.close();

        return recordItem;
    }

    /*
     *   查询所有记录
     * */
    public static ArrayList<RecordItem> queryAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<RecordItem> recordItemArrayList = new ArrayList<>();

        // 1. 获取数据库连接
        Connection connection = getConnection();

        // 2.创建preparedStatement
        String sql = "SELECT * FROM `accountUse1`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 4. 取出查询结果
        while (resultSet.next()) {
            RecordItem recordItem = new RecordItem();
            recordItem.setId((int) resultSet.getObject("id"));
            recordItem.setBalance((double) resultSet.getObject("balance"));
            recordItem.setLevel(RecordItem.str2level( (String)resultSet.getObject("level") ));
            recordItem.setCash((double) resultSet.getObject("cash"));
            recordItem.setComment((String) resultSet.getObject("comment"));
            recordItem.setDateTime(((LocalDateTime) resultSet.getObject("dateTime")).format(formatter));
            recordItemArrayList.add(recordItem);
        }

        // 5. 关闭资源
        preparedStatement.close();
        resultSet.close();
        connection.close();

        return recordItemArrayList;
    }

    /*
    *   插入一条记录
    * */
    public static void insertItem(RecordItem recordItem) throws SQLException, ClassNotFoundException {
        // 1. 获取数据库连接
        Connection connection = getConnection();

        // 2.创建preparedStatement
        String sql = "INSERT INTO `accountUse1` (`balance`, `level`, `cash`, `comment`) " +
                     "VALUES(?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, recordItem.getBalance());
        preparedStatement.setString(2, recordItem.getLevelStr());
        preparedStatement.setDouble(3, recordItem.getCash());
        preparedStatement.setString(4, recordItem.getComment());

        // 3. 发送 SQL 语句
        int rows = preparedStatement.executeUpdate();

        // 输出结果
        System.out.println("insert rows: " + rows);

        // 4. 关闭资源
        preparedStatement.close();
        connection.close();
    }


    /*
     *   清空所有记录
     * */
    public static void clearAllItems() throws SQLException, ClassNotFoundException {
        // 1. 获取数据库连接
        Connection connection = getConnection();

        // 2.创建preparedStatement
        String sql = "DELETE FROM `accountUse1`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 发送 SQL 语句
        int rows = preparedStatement.executeUpdate();

        // 输出结果
        System.out.println("delete rows: " + rows);

        // 4. 关闭资源
        preparedStatement.close();
        connection.close();
    }
}
