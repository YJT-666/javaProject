package com.jjt.ars.service;

import com.jjt.ars.javabean.Account;
import com.jjt.ars.javabean.AccountDao;
import com.jjt.ars.javabean.RecordLevel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ClassName: AccountService
 * Package: com.jjt.ars.service
 * Description:
 *              账户管理系统 业务对象类
 * @Author jjt
 * @Create 2023/12/14 13:49
 * @Version 1.0
 */
public class AccountService {

    private double balanceLive =  0;   // 当前实时余额
    private AccountDao accountDao = null; // 数据库 DAO对象

    public AccountService() {
        accountDao = new AccountDao();
        // 加载数据库当中最新的余额数据
        try {
            Account account = accountDao.getMaxIdItem();
            if (account != null) {
                balanceLive = account.getBalance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 添加记账记录
     *
     * @param account the account
     */
    public void addRecord(Account account) {

        // 计算余额
        if (account.getLevelMark() == RecordLevel.INCOME) {
            balanceLive += account.getCash();
        } else if (account.getLevelMark() == RecordLevel.OUTCOME) {
            balanceLive -= account.getCash();
        }
        account.setBalance(balanceLive);

        // 加入数据库
        try {
            accountDao.insertItem(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 清空账本
     */
    public void removeAllRecords() {
        try {
            accountDao.removeAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回所有记录
     *
     * @return the all records
     */
    public ArrayList<Account> getAllRecords() {
        try {
            return accountDao.queryAllItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
