package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.annotation.type.TypeAnnotationVerbose;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeVisibleTypeAnnotationsAttributeVerbose extends TypeAnnotationAttributeVerbose {

    public RuntimeVisibleTypeAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
    }
}
