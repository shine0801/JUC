package com.shine.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：Ajax
 * 异步执行
 * 成功回调
 * 失败回调
* */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求,无返回值的回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//        System.out.println("1111111111");
//        completableFuture.get();

        //有返回值的异步回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
          System.out.println(Thread.currentThread().getName()+"runAsync=>Integer");
            return 1024;
        });

        completableFuture.whenComplete((t, u)->{
            System.out.println("t=>"+t);
            System.out.println("u=>"+u);
        }).exceptionally((e)->{ //异常
            e.printStackTrace();
            return  233;
        }).get();



    }
}
