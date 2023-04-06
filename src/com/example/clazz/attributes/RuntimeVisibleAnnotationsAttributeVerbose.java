package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.AnnotationElement;
import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;
import com.example.clazz.dot.DotStyle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class RuntimeVisibleAnnotationsAttributeVerbose extends AttributeVerbose {
    int numAnnotations;
    AnnotationElement[] elements;

    public RuntimeVisibleAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numAnnotations = dis.readUnsignedShort();
        elements = new AnnotationElement[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            elements[i] = new AnnotationElement();
            elements[i].read(dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);

        DotItem numItem = new DotItem("number", String.valueOf(numAnnotations))
                .parent(superItem);
        superItem.addChild("num", numItem);

//        DotItem elementsItem = new DotItem("elements", "")
//                .parent(superItem).style(DotStyle.DASHED);
//        superItem.addChild("", elementsItem);
        for (int i = 0; i < numAnnotations; i++) {
            DotItem item = elements[i].createDotItem(classDot, superItem, i);
            superItem.addChild("", item);
        }

        return superItem;
    }
}
