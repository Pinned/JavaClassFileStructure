package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.ParameterAnnotationsAttributeVerbose;
import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleParameterAnnotationsAttributeVerbose extends ParameterAnnotationsAttributeVerbose {
    public RuntimeInvisibleParameterAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
