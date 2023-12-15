package com.jjt.bank;

/**
 * ClassName: ExeTest
 * Package: jjt.bank
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/12 16:00
 * @Version 1.0
 */
public class ExeTest {
    public static void main(String[] args) {
        Account account = new Account();

        Thread userThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    account.saveMoney(Thread.currentThread().getName(), 1000);
                }
            }
        }, "用户1");

        Thread userThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    account.withdrawMoney(Thread.currentThread().getName(), 1000);
                }
            }
        }, "用户2");

        userThread1.start();
        userThread2.start();

    }
}
