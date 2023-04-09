package com.example.clazz.attributes;


import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class DeprecatedAttributeVerbose extends AttributeVerbose {
    public DeprecatedAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
