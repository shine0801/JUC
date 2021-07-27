package com.shine.function;

import java.util.function.Predicate;

public class demo02 {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        Predicate<String> predicate1  = (str)->{
            return  str.isEmpty();
        };
        //返回是布尔值
        System.out.println(predicate.test(""));
    }
}
