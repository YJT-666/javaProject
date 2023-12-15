package com.jjt;

/**
 * ClassName: RecordItem
 * Package: jjt
 * Description:
 *              记录的表项类
 * @Author jjt
 * @Create 2023/12/13 13:42
 * @Version 1.0
 */
public class RecordItem {

    private static double balanceLive = 0;  // 记录实时总金额
    private int id;   // 当前项的序号
    private double balance;  // 当前项的总金额
    private RecordLevel level;
    private double cash;
    private String comment;

    private String dateTime;

    public RecordItem() {

    }

    public RecordItem(RecordLevel level, double cash, String comment) {
        this.level = level;
        this.cash = cash;
        this.comment = comment;
        if (level == RecordLevel.INCOME) {
            this.balanceLive += cash;
            this.balance = balanceLive;
        } else {
            this.balanceLive -= cash;
            this.balance = balanceLive;
        }
    }

    public static double getBalanceLive() {
        return balanceLive;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public RecordLevel getLevel() {
        return level;
    }

    public double getCash() {
        return cash;
    }

    public String getComment() {
        return comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public static void setBalanceLive(double balanceLive) {
        RecordItem.balanceLive = balanceLive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLevel(RecordLevel level) {
        this.level = level;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return id + "\t" + balance + "\t" + (level==RecordLevel.INCOME ? "收入": "支出")
                + "\t" + cash + "\t" + comment + "\t" + dateTime;
    }
    public static String level2Str(RecordLevel level) {
        return level==RecordLevel.INCOME ? "收入": "支出";
    }
    public static RecordLevel str2level(String string) {
        return string.equals("收入") ? RecordLevel.INCOME: RecordLevel.OUTCOME;
    }

    public String getLevelStr() {
        return level2Str(level);
    }
}
