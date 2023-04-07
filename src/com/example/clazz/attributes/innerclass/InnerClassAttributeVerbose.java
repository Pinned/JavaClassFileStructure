package com.example.clazz.attributes.innerclass;


import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class InnerClassAttributeVerbose extends AttributeVerbose {
    public int numberOfClasses;
    public InnerClass[] inners;

    public InnerClassAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numberOfClasses = dis.readUnsignedShort();
        inners = new InnerClass[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            inners[i] = new InnerClass(dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);
        DotItem number = new DotItem("number", String.valueOf(numberOfClasses))
                .parent(superItem);
        superItem.addChild("num", number);
        for (int i = 0; i < numberOfClasses; i++) {
            superItem.addChild("", inners[i].createDotItem(classDot, superItem, i));
        }
        return superItem;
    }
}
