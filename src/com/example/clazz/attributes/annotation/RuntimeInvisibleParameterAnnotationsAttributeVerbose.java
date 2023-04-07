package com.example.clazz.attributes.annotation;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleParameterAnnotationsAttributeVerbose extends RuntimeVisibleParameterAnnotationsAttributeVerbose {
    public RuntimeInvisibleParameterAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
    }
}
