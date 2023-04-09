package com.example.clazz.attributes;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.annotation.AnnotationsAttributeVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeVisibleAnnotationsAttributeVerbose extends AnnotationsAttributeVerbose {


    public RuntimeVisibleAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
