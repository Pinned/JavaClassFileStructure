package com.example.clazz.format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

@TypeAnnotation
public class TypeAnnotationExample {
    public @TypeAnnotation String abc;
    public List<@TypeAnnotation String> list;
    public @TypeAnnotation List<@TypeAnnotation String> list2;

    public @A Map<@B ? extends @C String, @D List<@E Object>> verbose;
//    public @D List<@E Object> verbose;
    public @TypeAnnotation String setup(@TypeAnnotation String abc) {
        this.abc = abc;
        return "abc";
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@interface A {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@interface B {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@interface C {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@interface D {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@interface E {

}