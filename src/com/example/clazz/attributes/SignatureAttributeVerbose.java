package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class SignatureAttributeVerbose extends AttributeVerbose {
    public int signatureIndex;

    public SignatureAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }


    @Override
    public void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException {
        signatureIndex = dataInputStream.readUnsignedShort();
    }

    @Override
    public void print(String parent, StringBuffer sb) {
        super.print(parent, sb);
        sb.append(getCurrentNodeName() + " -> constant_item_" + signatureIndex + "[label=\"signature\"]");
        sb.append(";\n");
    }
}
