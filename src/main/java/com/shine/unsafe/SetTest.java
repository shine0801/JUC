package com.shine.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

    /**
     * 解决方案：
     * 1- Set<String> set = Collections.synchronizedSet(new HashSet<>());
     * 2- Set<String> set = new CopyOnWriteArraySet<>();;
     * CopyOnWrite 写入时复制， 使用了lock锁，效率更高
     * 多个线程调用的的时候，list,读取的时候，固定的，写入覆盖
     * 在写的时候避免覆盖，造成数据问题
     * */

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            }).start();
        }
    }
}

