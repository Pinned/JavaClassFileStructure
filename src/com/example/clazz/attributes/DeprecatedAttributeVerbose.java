package com.example.clazz.attributes;


import java.io.DataInputStream;
import java.io.IOException;

public class DeprecatedAttributeVerbose extends AttributeVerbose {
    public DeprecatedAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
    }
}
