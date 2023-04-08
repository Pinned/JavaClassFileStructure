package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class NestMembersAttributeVerbose extends AttributeVerbose{
    public int numberOfClasses;
    public int[] classes;
    public NestMembersAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numberOfClasses = dis.readUnsignedShort();
        classes = new int[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        for (int i = 0; i < numberOfClasses; i++) {
            superDotItem.addChild("clz_" + i, classDot.getConstantItem(classes[i]));
        }
        return superDotItem;
    }
}
