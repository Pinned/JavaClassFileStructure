package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class DeprecatedAttributeVerbose extends AttributeVerbose {
    public DeprecatedAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }

    @Override
    public void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException {
        // 读取 Deprecated 字段
        dataInputStream.skipBytes(attributeLength);
    }
}
