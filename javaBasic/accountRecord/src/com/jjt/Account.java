package com.jjt;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ClassName: Account
 * Package: jjt
 * Description:
 * 基于文本界面实现的记账软件
 *
 * @Author jjt
 * @Create 2023 /12/12 17:26
 * @Version 1.0
 */
public class Account {

    private static ArrayList<RecordItem> recordItems;

    public static void main(String[] args) {

        // 加载数据库当中最新的余额数据
        try {
            DatabaseUtils.queryLastItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String menuSelection = "";
        while(true) {
            printSimpleUi();
            menuSelection = IoUtils.readMenuSelection();
            // System.out.println("menuSelection : " + menuSelection);
            if ( menuSelection.equals("q") ) {
                System.out.println("Bye!");
                break;
            }
            switch (menuSelection) {
                case "1":
                    printRecord();
                    break;
                case "2":
                    recordIncome();
                    break;
                case "3":
                    recordOutcome();
                    break;
                case "4":
                    recordClear();
                    break;
                default:
                    break;
            }
        }
    }

    // 打印菜单
    static void printSimpleUi() {
        System.out.println("\n-----------------文本记账软件-----------------\n");
        System.out.println("                   1 收支明细");
        System.out.println("                   2 登记收入");
        System.out.println("                   3 登记支出");
        System.out.println("                   4 清空账本");
        System.out.println("                   q 退    出\n");
        System.out.print("                   请选择：");
    }

    // 打印记录
    public static void printRecord() {

        try {
            recordItems = DatabaseUtils.queryAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("记录总数：" +  recordItems.size());
        System.out.println("序号" + "\t\t" + "账户余额" + "\t\t" + "收支" + "\t\t" + "金额" + "\t\t" + "说明" + "\t\t" + "日期");
        for (RecordItem item : recordItems) {
            System.out.println(item);
        }
    }

    // 登记收入
    public static void recordIncome() {
        double income = 0;
        System.out.print("本次收入金额（0）取消: ");
        income = IoUtils.readNumber();
        if (income == 0) return;
        // System.out.println("income " + income);
        System.out.print("本次收入说明（q）取消: ");
        String illustrate = IoUtils.readString();
        if (illustrate.equals("q")) return;
        // System.out.println("illustrate " + illustrate);
        RecordItem recordItem = new RecordItem(RecordLevel.INCOME, income, illustrate);

        // 加入到数据库
        try {
            DatabaseUtils.insertItem(recordItem);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // 登记支出
    public static void recordOutcome() {
        double Outcome = 0;
        System.out.print("本次支出金额（0）取消:: ");
        Outcome = IoUtils.readNumber();
        if (Outcome == 0) return;
        System.out.print("本次支出说明（q）取消:: ");
        String illustrate = IoUtils.readString();
        if (illustrate.equals("q")) return;

        RecordItem recordItem = new RecordItem(RecordLevel.OUTCOME, Outcome, illustrate);

        // 加入到数据库
        try {
            DatabaseUtils.insertItem(recordItem);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 清空账本
    public static void recordClear() {
        System.out.print("确认执行清空账本吗？确认(Y)：");
        String ans = IoUtils.readKeyBoard(1);
        if (ans.equals("Y") || ans.equals("y")) {
            // 执行清空操作
            try {
                DatabaseUtils.clearAllItems();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("清空了账本");
        } else {
            System.out.println("取消清空账本");
        }
    }
}


