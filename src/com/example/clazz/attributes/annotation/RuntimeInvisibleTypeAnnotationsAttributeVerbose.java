package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.annotation.type.TypeAnnotationVerbose;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleTypeAnnotationsAttributeVerbose extends TypeAnnotationAttributeVerbose {

    public RuntimeInvisibleTypeAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
    }
}
