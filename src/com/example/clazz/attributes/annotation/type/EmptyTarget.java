package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;

public class EmptyTarget {

    public EmptyTarget(DataInputStream dis) {
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("empty_target", "empty_target").parent(typeAnnotationDot);
    }
}
