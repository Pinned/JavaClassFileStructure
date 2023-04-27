package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class PermittedSubclassesAttributeVerbose extends AttributeVerbose {
    public int numberOfClasses;
    public int[] classes;

    public PermittedSubclassesAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        numberOfClasses = dis.readUnsignedShort();
        classes = new int[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem parentDotItem = super.createDotItem(classDot, parent, index);
        parentDotItem.addChild("number", new DotItem("number", String.valueOf(numberOfClasses))
                .parent(parentDotItem));

        DotItem classDotItem = new DotItem("classes", String.valueOf(numberOfClasses) + "个")
                .parent(parentDotItem).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        parentDotItem.addChild("child", classDotItem);
        for (int i = 0; i < numberOfClasses; i++) {
            ArrayDotItem arrayDotItem = new ArrayDotItem("classes", i, "")
                    .parent(classDotItem).style(DotStyle.DASHED);
            arrayDotItem.addChild("", classDot.getConstantItem(classes[i]));
            classDotItem.addChild("classes", arrayDotItem);
        }

        return parentDotItem;
    }
}
