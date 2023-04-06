package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class CodeAttributeVerbose extends AttributeVerbose {


    public CodeAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        // TODO 此处先跳过具体内容
        dis.skipBytes(attributeLength);
    }
}
