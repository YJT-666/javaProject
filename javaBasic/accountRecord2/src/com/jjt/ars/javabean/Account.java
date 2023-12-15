package com.jjt.ars.javabean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: Account
 * Package: com.jjt.ars.javabean
 * Description:
 *               数据库实体对象类
 * @Author jjt
 * @Create 2023/12/14 13:43
 * @Version 1.0
 */
public class Account {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;                // 当前项的序号
    private double balance;       // 当前项的总金额
    private String level;   // 记录级别
    private double cash;        // 记录金额
    private String comment;     // 说明

    private LocalDateTime dateTime;    // 时间

    public Account() {
    }

    public Account(RecordLevel recordLevel, double cash, String comment) {
        this.level = levelMark2Str(recordLevel);
        this.cash = cash;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return id + "\t" + balance + "\t" + level
                + "\t" + cash + "\t" + comment + "\t" + dateTime.format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public RecordLevel getLevelMark() {
        return str2levelMark(this.level);
    }
    public static String levelMark2Str(RecordLevel level) {
        return level==RecordLevel.INCOME ? "收入": "支出";
    }
    public static RecordLevel str2levelMark(String string) {
        return string.equals("收入") ? RecordLevel.INCOME: RecordLevel.OUTCOME;
    }
}
