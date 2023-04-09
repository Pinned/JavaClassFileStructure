package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.AnnotationsAttributeVerbose;
import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleAnnotationsAttributeVerbose extends AnnotationsAttributeVerbose {

    public RuntimeInvisibleAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
