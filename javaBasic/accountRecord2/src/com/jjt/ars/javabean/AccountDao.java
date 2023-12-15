package com.jjt.ars.javabean;

import com.jjt.ars.JDBCUtils.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ClassName: AccountDao
 * Package: com.jjt.ars.javabean
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/14 16:02
 * @Version 1.0
 */
public class AccountDao extends BaseDao {

    /**
     * 获取 记录 id 最大的项
     *
     * @return the max id item
     * @throws Exception the exception
     */
    public Account getMaxIdItem() throws Exception {
        String sql = "SELECT * \n" +
                "FROM `accountUse1`\n" +
                "WHERE `id`=(\n" +
                "    SELECT MAX(`id`) \n" +
                "    FROM `accountUse1`\n" +
                ")\n" +
                ";";
        return executeQueryBean(Account.class, sql);
    }

    /**
     * 查询数据库当中所有的记录
     *
     * @return the array list
     * @throws Exception the exception
     */
    public ArrayList<Account> queryAllItems() throws Exception {
        String sql = "SELECT * FROM `accountUse1`;";
        return executeQuery(Account.class, sql);
    }

    /**
     *  向数据库插入一条记录
     *
     * @param account the account
     * @return the int
     * @throws SQLException the sql exception
     */
    public int insertItem(Account account) throws SQLException {
        String sql = "INSERT INTO `accountUse1` (`balance`, `level`, `cash`, `comment`) " +
                "VALUES(?, ?, ?, ?);";
        return executeUpdate(sql, account.getBalance(),
                account.getLevel(), account.getCash(), account.getComment());
    }

    /**
     * 清空数据库表当中的所有记录
     */
    public int removeAllItems() throws SQLException {
        String sql = "DELETE FROM `accountUse1`;";
        return executeUpdate(sql);
    }
}
