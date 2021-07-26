package com.shine.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<>()).start();
        // new Thread(new FutureTask<>(Callable)).start();

        //new Thread().start(); //怎么启动Callable

        MyThread thread = new MyThread();

        FutureTask<String> futureTask = new FutureTask<>(thread); //适配类

        new Thread(futureTask, "a").start();
        new Thread(futureTask, "b").start(); //结果会被缓存，效率高

        String s = (String)futureTask.get(); //get方法可能会产生阻塞，放到最后一行或者使用异步通信
        System.out.println(s);
    }

}

class MyThread implements Callable<String> { //返回值是String
    @Override
    public String  call() {
        return "fcdv";

    }
}
