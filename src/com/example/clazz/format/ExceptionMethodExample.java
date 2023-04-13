package com.example.clazz.format;

import java.io.IOException;

public class ExceptionMethodExample {

    public int setup()  {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public void config() throws IOException, InterruptedException {
        throw new RuntimeException("config error");
    }

    public static void main(String[] args) {
        ExceptionMethodExample example = new ExceptionMethodExample();
        System.out.println(example.setup().x);
    }
    
}
