package com.jjt.saleTickets;

/**
 * ClassName: TicketSaleThread
 * Package: jjt.saleTickets
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/12 16:27
 * @Version 1.0
 */
public class TicketSaleThread extends Thread {
    private static  int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            saleOneTicket();
        }
    }

    public synchronized static void saleOneTicket() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出一张票, 票号: " + ticket);
            ticket--;
        }
    }
}


class SaleTicketsDemo {
    public static void main(String[] args) {
        TicketSaleThread ticketSaleThread1 = new TicketSaleThread();
        TicketSaleThread ticketSaleThread2 = new TicketSaleThread();
        TicketSaleThread ticketSaleThread3 = new TicketSaleThread();

        ticketSaleThread1.setName("窗口1");
        ticketSaleThread2.setName("窗口2");
        ticketSaleThread3.setName("窗口3");

        ticketSaleThread1.start();
        ticketSaleThread2.start();
        ticketSaleThread3.start();
    }
}