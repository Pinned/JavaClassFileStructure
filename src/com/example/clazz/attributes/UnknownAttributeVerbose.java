package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class UnknownAttributeVerbose extends AttributeVerbose {
    public byte[] data;

    public UnknownAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }


    @Override
    public void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException {
        // 解析未知属性
        data = new byte[attributeLength];
        dataInputStream.read(data, 0, attributeLength);
    }

}
