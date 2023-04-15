package com.example.clazz.format;

import java.util.List;

public class GenericMethodExample {

    public <T> void setup(T abc) {

    }


    public void setup(List<String> abc) {
        for (String a : abc) {
            System.out.println(a);
        }
    }

}
