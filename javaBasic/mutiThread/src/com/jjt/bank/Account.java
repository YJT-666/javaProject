package com.jjt.bank;

/**
 * ClassName: Account
 * Package: jjt.bank
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/12 15:48
 * @Version 1.0
 */
public class Account {
    private double money;

    public Account(double money) {
        this.money = money;
    }

    public Account() {
        this.money = 0;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }


    public double withdrawMoney(String name, double money) {
        synchronized (this) {
            if (this.money >= money) {
                this.money -= money;
                System.out.println(name + " withdraw money: " + money + "\t" + "left money: " + this.money);
            } else {
                System.out.println(name + " oops! you have not enough money" + "left money: " + this.money);
            }
            return money;
        }
    }

    public double saveMoney(String name, double money) {
        synchronized (this) {
            if (money > 0) {
                this.money += money;
                System.out.println(name + " save money: " + money + "\t" + "left money: " + this.money);
            }
        }
        return money;
    }
}
