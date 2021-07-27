package com.shine.function;


import java.util.function.Consumer;

//消费型接口:只有输入，没有返回值
public class demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> consumer1 = (str)->{
            System.out.println(str);
        };
        consumer1.accept("zxx");
    }
}
