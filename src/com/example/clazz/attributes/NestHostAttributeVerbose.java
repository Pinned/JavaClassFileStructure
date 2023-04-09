package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class NestHostAttributeVerbose extends AttributeVerbose{
    public int hostClassIndex;

    public NestHostAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        hostClassIndex = dis.readUnsignedShort();
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem =  super.createDotItem(classDot, parent, index);
        superDotItem.addChild("host_clz", classDot.getConstantItem(hostClassIndex));
        return superDotItem;
    }
}
