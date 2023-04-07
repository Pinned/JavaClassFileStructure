package com.example.clazz.format;

import java.util.List;
import java.util.Map;

public class VerboseSignature<T> {
    public T typeVariable;
    Map<String, Integer> parameterizedType;

    public void setupSuperParams(List<? super ClassFormat> data) {

    }

    public void setupExtendsParams(List<? extends ClassFormat> data) {

    }

    public void setupExtendsT(List<? extends T> data) {

    }

    public void setup(List<?> data) {
        
    }
}


