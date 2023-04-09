package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class UnknownAttributeVerbose extends AttributeVerbose {
    public byte[] data;

    public UnknownAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        // 解析未知属性
        data = new byte[attributeLength];
        dis.read(data, 0, attributeLength);
    }

}
