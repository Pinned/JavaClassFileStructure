package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class ExceptionAttributeVerbose extends AttributeVerbose{
    public int numberOfExceptions;
    public int[] exceptionIndexTable;
    public ExceptionAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numberOfExceptions = dis.readUnsignedShort();
        exceptionIndexTable = new int[numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem =  super.createDotItem(classDot, parent, index);
        DotItem dotItem = new DotItem("number_of_exceptions", numberOfExceptions + "")
                .parent(superDotItem);
        superDotItem.addChild("num", dotItem);
        for (int i = 0; i < numberOfExceptions; i++) {
            superDotItem.addChild("ex_" + i, classDot.getConstantItem(exceptionIndexTable[i]));
        }
        return superDotItem;
    }
}
