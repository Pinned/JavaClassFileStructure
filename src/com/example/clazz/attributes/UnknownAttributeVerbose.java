package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class UnknownAttributeVerbose extends AttributeVerbose {
    public byte[] data;

    public UnknownAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        // 解析未知属性
        data = new byte[attributeLength];
        dis.read(data, 0, attributeLength);
    }

}
