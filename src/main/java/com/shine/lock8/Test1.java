package com.shine.lock8;

import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone =new Phone();
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone.call();
        },"B").start();
    }

}

class Phone{
    //synchronized锁的对象是方法的调用者
    //两个方法用的是同一把锁，谁先拿到谁执行
    public synchronized void sendSms(){
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");

    }

    /**
     *8锁。就是关于锁的8个问题
     *1-标准情况下，两个线程先打印 “发短信 ” 还是 “打电话”？ 先发短信，后打电话（并不是因为先调用，先执行）
     *2-发短信方法， 若延迟4秒，还是先发短信，后打电话
     * */

}
