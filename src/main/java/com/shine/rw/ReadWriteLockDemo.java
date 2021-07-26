package com.shine.rw;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        //只做写入操作
        for (int i = 1; i < 6; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            }, String.valueOf(i)).start();
        }

        //只做读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }

}

/**
 * 自定义缓存
 * */
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    //存，写
    public  void put(String key, Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入完毕");

    }

    //取，读
    public  void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完毕");
    }

}

/**
 * 自定义缓存
 * 独占锁(写锁) 一次只能被一个线程占用
 * 共享锁（读锁） 多个线程可以同时占有
 * 读-读 可以一起
 * 读-写 不能
 * 写-写 不能
 * */
class MyCacheLock{
    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁，更加细粒度的控制
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存，写，写入的时候只希望同时只有一个线程
    public  void put(String key, Object value){

        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    //取，读，所有人都可以读
    public  void get(String key){

        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}