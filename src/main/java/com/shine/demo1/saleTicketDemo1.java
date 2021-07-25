package com.shine.demo1;


//基本的卖票例子
/***
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1-属性、方法
 */

public class saleTicketDemo1 {
    public static void main(String[] args) {
        //并发：操作同一个资源类
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}


class Ticket{
    //属性、方法
    private int number = 50;
    //卖票
    synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票");
        }
    }
}
