package com.shine.lock8;

import java.util.concurrent.TimeUnit;

public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        Phone3 phone1 =new Phone3();
        Phone3 phone2 =new Phone3();
        new Thread(()->{
            phone1.sendSms(); //发短信
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone2.call(); //打电话
        },"B").start();
    }

}

class Phone3{
    //synchronized锁的对象是方法的调用者
    //两个方法用的是同一把锁，谁先拿到谁执行

    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");

    }
    }

    /**
     *8锁。就是关于锁的8个问题
     *1-标准情况下，两个线程先打印 “发短信 ” 还是 “打电话”？ 先发短信，后打电话（并不是因为先调用，先执行）
     *2-发短信方法， 若延迟4秒，还是先发短信，后打电话
     *3-增加一个普通方法后，先执行发短信还是普通方法？ 普通方法
     *4-两个对象，两个同步方法，先打电话
     *5-静态方法，一个对象：先发短信，锁的是Phone.class模板
     *6-静态对象，两个对象：还是先发短信，因为是锁的Phone.class模板，和对象无关的。
     *
     * */


