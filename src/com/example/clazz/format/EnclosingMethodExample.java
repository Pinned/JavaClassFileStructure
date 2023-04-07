package com.example.clazz.format;

public class EnclosingMethodExample {
    public void setup() {
        class A {
            String a = "10";
        }
        A a = new A();
        System.out.println(a.a);
    }
}
