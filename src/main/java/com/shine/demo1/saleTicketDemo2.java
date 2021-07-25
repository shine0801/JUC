package com.shine.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicketDemo2 {
    public static void main(String[] args) {
        //并发：操作同一个资源类
        Ticket2 ticket = new Ticket2();
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

/**
 * lock 三部曲
 * 1- new ReenTrantLock()
 * 2-加锁
 * 3-解锁
 * */
class Ticket2{
    //属性、方法
    private int number = 50;
    Lock lock = new ReentrantLock();
    //卖票
    synchronized void sale(){
        lock.lock();//加锁
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//解锁
        }
    }
}
