package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParameterAnnotationsAttributeVerbose extends AttributeVerbose {
    int numParameters;
    List<AnnotationElementArray> parameterAnnotations = new ArrayList<>();

    public ParameterAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        numParameters = dis.readByte();
        for (int i = 0; i < numParameters; i++) {
            parameterAnnotations.add(new AnnotationElementArray(attributeNameIndex, attributeName, dis));
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);
        DotItem paramItem = new DotItem("params_number", String.valueOf(numParameters) + "个\\n参数")
                .parent(superItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        superItem.addChild("", paramItem);
        for (int i = 0; i < numParameters; i++) {
            DotItem item = createAnnotationDotItem(classDot, paramItem, i, parameterAnnotations.get(i));
            paramItem.addChild("", item);
        }


        return superItem;
    }

    private DotItem createAnnotationDotItem(ClassDot classDot, DotItem parent, int index, AnnotationElementArray array) {
        ArrayDotItem emptyItem = new ArrayDotItem("annotation", index, "")
                .parent(parent).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        DotItem numItem = new DotItem("number", String.valueOf(array.numAnnotations))
                .parent(emptyItem);
        emptyItem.addChild("num", numItem);
        for (int i = 0; i < array.numAnnotations; i++) {
            DotItem item = array.elements[i].createDotItem(classDot, emptyItem, i);
            emptyItem.addChild("", item);
        }
        return emptyItem;
    }

}
