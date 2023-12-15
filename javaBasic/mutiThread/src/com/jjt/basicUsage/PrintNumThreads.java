package com.jjt.basicUsage;

/**
 * ClassName: PrintNumThreads
 * Package: jjt.basicUsage
 * Description:
 * 创建两个分线程，让其中一个线程输出1-100之间的偶数，另一个线程输出1-100之间的奇数。
 *
 * @Author jjt
 * @Create 2023 /12/12 14:40
 * @Version 1.0
 */
public class PrintNumThreads {

    public static void main(String[] args) {
        example1();
    }

    /**
     * 通过创建匿名类对象创建线程
     * 1. 调用 public Thread(String name) 创建 Thread 对象
     *      重写 run 方法
     *      调用 strat 方法启动线程
     * 2. 调用 public Thread(Runnable target) 创建 Thread 对象
     *      target 对象要实现 Runnable 接口
     */
    public static void example1() {
        new Thread("线程1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i < 101; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "打印" + i);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i < 101; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + "打印" + i);
                    }
                }
            }
        }, "线程2").start();
    }
}
