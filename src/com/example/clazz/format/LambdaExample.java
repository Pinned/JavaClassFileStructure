package com.example.clazz.format;

public class LambdaExample {

    public void print() {
        Runnable r = () -> System.out.println("Hello world");
        r.run();
    }

}
