package com.jjt.consumerProduce;

/**
 * ClassName: ConsumerProducer
 * Package: jjt.consumerProduce
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/12 16:49
 * @Version 1.0
 */
public class ConsumerProducer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        ProducerThread producerThread = new ProducerThread(clerk);
        Consumer consumer = new Consumer(clerk);
        producerThread.setName("生产者");
        consumer.setName("消费者");

        producerThread.start();
        consumer.start();
    }
}

/**
 职员类，直接负责对产品生产和消费进行记录
 */
class Clerk {
    /**
        产品           属于共享属性
        最大产品个数
        最小产品个数
    */
    private int products;
    private static final int MAX_PRODUCTS = 20;
    private static final int MIN_PRODUCTS = 0;

    public Clerk() {
        products = MIN_PRODUCTS;
    }

    /**
        记录增加产品，对共享属性操作，需要线程同步
        添加产品
            如果产品满了的话，暂停添加，并阻塞自身
            添加成功的话，唤醒消费产品的线程
    */
    public void addProduct() {
        synchronized (this) {
            if (products < MAX_PRODUCTS) {
                products++;
                System.out.println(Thread.currentThread().getName() + "生产了第" + products + "个产品");
                // 生产了新的产品，可以唤醒消费线程
                this.notifyAll();
            } else {
                try {
                    this.wait();    // 产品满了，阻塞生产线程
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
        记录消费产品
        消费产品
            如果产品空了的话，暂停消费，并阻塞自身
            消费成功的话，唤醒生产品的线程
    */
    public void minusProduct() {
        synchronized (this) {
            if (products > MIN_PRODUCTS) {
                System.out.println(Thread.currentThread().getName() + "消费了第" + products + "个产品");
                products--;
                this.notifyAll();
            } else {
                try {
                    wait();   // 没有产品消费了，阻塞自身
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

// 生产者线程
class ProducerThread extends Thread {
    private Clerk clerk;   // 只向记录产品的职员

    public ProducerThread(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("=========生产者开始生产产品========");
        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.addProduct();  // 要求 clerk 去增加产品
        }
    }
}

// 消费者线程
class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("=========消费者开始消费产品========");
        while (true) {
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.minusProduct();  // 要求 clerk 去减少产品
        }

    }
}