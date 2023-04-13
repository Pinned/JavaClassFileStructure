package com.example.clazz.format;

import java.io.IOException;

public class ExceptionMethodExample {

    public int setup()  {
        int x;
        try {
            x = 1;
            return x;
        } catch (IllegalArgumentException e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }

    public void config() throws IOException, InterruptedException {
        throw new RuntimeException("config error");
    }

}
