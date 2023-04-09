package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class CodeAttributeVerbose extends AttributeVerbose {


    public CodeAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        // TODO 此处先跳过具体内容
        dis.skipBytes(attributeLength);
    }
}
