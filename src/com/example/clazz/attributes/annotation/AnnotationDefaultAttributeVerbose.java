package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class AnnotationDefaultAttributeVerbose extends AttributeVerbose {

    public AnnotationElementValue elementValue;

    public AnnotationDefaultAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        elementValue = new AnnotationElementValue();
        elementValue.read(dis);
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem defaultItem = elementValue.createDotItem(classDot, superDotItem, 0);
        superDotItem.addChild("default", defaultItem);
        return superDotItem;
    }
}
