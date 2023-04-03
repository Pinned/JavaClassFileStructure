package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class CodeAttributeVerbose extends AttributeVerbose {


    public CodeAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }

    @Override
    public void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException {
        dataInputStream.skipBytes(attributeLength);
    }
}
