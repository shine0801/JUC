package com.shine.bq;

import java.sql.Time;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    //抛出异常
    public static void test1(){

        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("d"));



        System.out.println("========");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.element()); //查看队首元素

        //"main" java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }

    //不抛异常
    public static void test2(){

        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //返回值false
        //System.out.println(blockingQueue.offer("d"));

        System.out.println("==================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //返回值null
        System.out.println(blockingQueue.poll());

        //返回null
        System.out.println(blockingQueue.peek());
    }

    //等待，阻塞（一直阻塞）
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        //blockingQueue.put("d"); //队列没有位置了，就会一直等待

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        System.out.println(blockingQueue.take());//没有这个元素，一直阻塞

    }


    // 等待，阻塞， 等待超时
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        blockingQueue.offer("d", 2,TimeUnit.SECONDS); //等待超过两秒就退出

        System.out.println("============");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS)); //等待两秒退出

    }



}
