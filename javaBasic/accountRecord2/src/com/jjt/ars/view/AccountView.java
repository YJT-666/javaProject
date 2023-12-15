package com.jjt.ars.view;

import com.jjt.ars.javabean.Account;
import com.jjt.ars.javabean.RecordLevel;
import com.jjt.ars.service.AccountService;

import java.util.ArrayList;

/**
 * ClassName: AccountView
 * Package: com.jjt.ars.view
 * Description:
 *              账户管理系统视图对象类
 * @Author jjt
 * @Create 2023/12/14 13:47
 * @Version 1.0
 */
public class AccountView {


    private static AccountService accountService = null;
    static {
        accountService = new AccountService();
    }

    public static void main(String[] args) {


        String menuSelection = "";
        while(true) {
            printSimpleUi();
            menuSelection = KeyboardUtility.readMenuSelection();
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

    public static void printSimpleUi() {
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

        ArrayList<Account> recordItems = null;
        try {
            recordItems = accountService.getAllRecords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("记录总数：" +  recordItems.size());
        System.out.println("序号" + "\t\t" + "账户余额" + "\t\t" + "收支" + "\t\t" + "金额" + "\t\t" + "说明" + "\t\t" + "日期");
        for (Account item : recordItems) {
            System.out.println(item);
        }
    }

    // 登记收入
    public static void recordIncome() {

        // 与用户交互获取信息
        System.out.print("本次收入金额（0）取消: ");
        double income = KeyboardUtility.readNumber();
        if (income == 0) return;
        System.out.print("本次收入说明（q）取消: ");
        String illustrate = KeyboardUtility.readString();
        if (illustrate.equals("q")) return;

        // 调用账本服务，将用户输入的信息更新到数据库
        accountService.addRecord(new Account(RecordLevel.INCOME, income, illustrate));

    }
    // 登记支出
    public static void recordOutcome() {
        // 与用户交互获取信息
        System.out.print("本次支出金额（0）取消: ");
        double outcome = KeyboardUtility.readNumber();
        if (outcome == 0) return;
        System.out.print("本次支出说明（q）取消:: ");
        String illustrate = KeyboardUtility.readString();
        if (illustrate.equals("q")) return;

        // 调用账本服务，将用户输入的信息更新到数据库
        accountService.addRecord(new Account(RecordLevel.OUTCOME, outcome, illustrate));
    }

    // 清空账本
    public static void recordClear() {
        System.out.print("确认执行清空账本吗？确认(Y)：");
        String ans = KeyboardUtility.readKeyBoard(1);
        if (ans.equals("Y") || ans.equals("y")) {
            // 执行清空操作
            accountService.removeAllRecords();
            System.out.println("清空了账本");
        } else {
            System.out.println("取消清空账本");
        }
    }
}
