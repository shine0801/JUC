package com.shine.commonUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        //总数是6， 必须要执行的任务的时候使用
        CountDownLatch latch = new CountDownLatch(6);


        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"has go out!");
                latch.countDown(); //-1
            },String.valueOf(i)).start();
        }

        //等待这个计数器归0
        latch.await();

        System.out.println("关门");
    }
}
