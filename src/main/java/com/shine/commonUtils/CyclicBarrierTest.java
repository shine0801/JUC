package com.shine.commonUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        /**
        * 集齐7颗龙珠召唤神龙
        * */

        //召唤神龙的线程

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功");
        });


        //召唤龙珠的线程
        for (int i = 1; i <=7 ; i++) {
            final int temp = i; //lamda表达式不能够操作i，但是能够拿到final的值
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"手机了"+temp+"个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }


    }


}
