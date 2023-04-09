package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class EnclosingMethodAttributeVerbose extends AttributeVerbose {
    public int classIndex;
    public int methodIndex;

    public EnclosingMethodAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        classIndex = dis.readUnsignedShort();
        methodIndex = dis.readUnsignedShort();
    }


    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);
        superItem.addChild("class", classDot.getConstantItem(classIndex));
        superItem.addChild("method", classDot.getConstantItem(methodIndex));
        return superItem;
    }
}
