package com.shine.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException 并发修改异常
public class ListTest {
    public static void main(String[] args) {

        //并发下，ArrayList不安全。
        /**
         * 解决方案：
         * 1- List<String> list = new Vector<>()   解决，是安全的,使用了synchronized锁
         * 2- List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3- List<String> list = new CopyOnWriteArrayList<>();    CopyOnWrite 写入时复制， 使用了lock锁，效率更高
         * 多个线程调用的的时候，list,读取的时候，固定的，写入覆盖
         * 在写的时候避免覆盖，造成数据问题
         * */
        List<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 1000; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
