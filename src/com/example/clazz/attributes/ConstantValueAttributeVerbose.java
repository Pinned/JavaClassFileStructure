package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class ConstantValueAttributeVerbose extends AttributeVerbose {

    private int constantValueIndex;

    public ConstantValueAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        constantValueIndex = dis.readUnsignedShort();
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);
        superItem.addChild("constant_value", classDot.getConstantItem(constantValueIndex));
        return superItem;
    }
}
