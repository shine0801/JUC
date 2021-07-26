package com.shine.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapTest {

    public static void main(String[] args) {


        //map是这样用的吗？ 不是，工作中不用hashMap
        Map<String, String> map = new ConcurrentHashMap<>();
        //加载因子、初始化容量
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }).start();

        }

    }
}
