package com.shine.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                data.printB();
            }

        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class Data3{

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;


    public void printA(){
        lock.lock();
        try {
            while(num!=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了A");
            //唤醒指定的线程
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void printB(){
        lock.lock();
        try {
            while(num!=2){
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了B");
            //唤醒指定的线程
            num = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printC(){
        lock.lock();
        try {
            while(num!=3){
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了C");
            //唤醒指定的线程
            num = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


