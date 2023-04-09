package com.example.clazz.format;

public record Person(String name, int age, String hometown) {
    public Person {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}