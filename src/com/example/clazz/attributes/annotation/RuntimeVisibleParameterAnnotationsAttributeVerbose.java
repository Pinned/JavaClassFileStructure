package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.annotation.AnnotationElement;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;
import com.example.clazz.dot.DotShape;
import com.example.clazz.dot.DotStyle;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RuntimeVisibleParameterAnnotationsAttributeVerbose extends AttributeVerbose {
    int numParameters;
    List<AnnotationElementArray> parameterAnnotations = new ArrayList<>();

    public RuntimeVisibleParameterAnnotationsAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numParameters = dis.readByte();
        for (int i = 0; i < numParameters; i++) {
            parameterAnnotations.add(new AnnotationElementArray(attributeNameIndex, attributeName, dis));
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);

        DotItem paramItem = new DotItem("params_number", String.valueOf(numParameters))
                .parent(superItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        superItem.addChild("", paramItem);
        for (int i = 0; i < numParameters; i++) {
            createAnnotationDotItem(classDot, paramItem, i, parameterAnnotations.get(i));
        }


        return superItem;
    }

    private void createAnnotationDotItem(ClassDot classDot, DotItem parent, int index, AnnotationElementArray array) {
        DotItem numItem = new DotItem("number_" + index, String.valueOf(array.numAnnotations))
                .parent(parent);
        parent.addChild("num", numItem);
        for (int i = 0; i < array.numAnnotations; i++) {
            DotItem item = array.elements[i].createDotItem(classDot, parent, i);
            parent.addChild("", item);
        }
    }

}
