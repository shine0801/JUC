package com.shine.function;

import java.util.function.Function;

public class demo01 {
    public static void main(String[] args) {

        //工具类：输出输入的值
        Function function = new Function<String,String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };


        //lamda表达式
       Function function1 = (str)->{ return str;};

        System.out.println(function.apply("asd"));
    }
}
