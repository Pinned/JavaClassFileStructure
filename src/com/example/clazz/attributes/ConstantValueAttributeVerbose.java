package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class ConstantValueAttributeVerbose extends AttributeVerbose {

    private int constantValueIndex;

    public ConstantValueAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }


    @Override
    public void readAttribute(int attributeLength, DataInputStream dis) throws IOException {
        constantValueIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(String parent, StringBuffer sb) {
        super.print(parent, sb);
        sb.append(getCurrentNodeName() + " -> constant_item_" + constantValueIndex + "[label=\"constantValue\"]");
        sb.append(";\n");
    }
}
