package com.example.clazz.format;

import java.util.List;

@TypeAnnotation
public class TypeAnnotationExample {
    public @TypeAnnotation String abc;
    public List<@TypeAnnotation String> list;
    public @TypeAnnotation List<@TypeAnnotation String> list2;

    public @TypeAnnotation String setup(@TypeAnnotation String abc) {
        this.abc = abc;
        return "abc";
    }
}
