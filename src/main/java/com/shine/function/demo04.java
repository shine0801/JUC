package com.shine.function;

import java.util.function.Supplier;

//供给性接口：没有参数，只有返回值
public class demo04 {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };

        Supplier supplier1 = ()->{
            return 1024;
        };
    }
}
