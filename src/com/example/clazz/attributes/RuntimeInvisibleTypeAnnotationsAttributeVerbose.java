package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.TypeAnnotationAttributeVerbose;
import com.example.clazz.attributes.annotation.type.TypeAnnotationVerbose;
import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleTypeAnnotationsAttributeVerbose extends TypeAnnotationAttributeVerbose {


    public RuntimeInvisibleTypeAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
