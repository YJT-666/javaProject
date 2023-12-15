package com.jjt.alternatePrint;

/**
 * ClassName: AlternatePrintThread
 * Package: jjt.alternatePrint
 * Description:
 *                  使用两个线程打印 1-100。线程1, 线程2 交替打印
 * @Author jjt
 * @Create 2023/12/12 16:36
 * @Version 1.0
 */
public class AlternatePrintThread implements Runnable {
    int number = 1;
    @Override
    public void run() {

        while(this.number < 101) {
            synchronized (this) {
                notify();   // 唤醒处于 wait 状态的线程
                if (this.number < 101) {
                    System.out.println(Thread.currentThread().getName() + ":" + this.number++);
                }
                try {
                    wait();   // 打印完毕，本线程进入 wait 状态
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}


class PrintNums {
    public static void main(String[] args) {
        AlternatePrintThread alternatePrintThread = new AlternatePrintThread();
        Thread thread1 = new Thread(alternatePrintThread, "线程1");
        Thread thread2 = new Thread(alternatePrintThread, "线程2");

        thread1.start();
        thread2.start();
    }
}