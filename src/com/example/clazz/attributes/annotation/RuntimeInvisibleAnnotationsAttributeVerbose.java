package com.example.clazz.attributes.annotation;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleAnnotationsAttributeVerbose extends RuntimeVisibleAnnotationsAttributeVerbose {
    public RuntimeInvisibleAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
    }
}
