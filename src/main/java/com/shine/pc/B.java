package com.shine.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//JUC版本的生产消费者模式
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

class Data2{
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public  void increment() throws InterruptedException {
        lock.lock();
        try{
            while(number!=0){ //防止虚假唤醒问题
                condition.await(); //等待
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我+1完毕了
            condition.signalAll();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock(); //释放锁
        }


    }

    public synchronized void decrement() throws InterruptedException {
        lock.lock();

        try {
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我-1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
