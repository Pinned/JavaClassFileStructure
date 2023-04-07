package com.example.clazz.attributes.annotation;

import java.io.DataInputStream;
import java.io.IOException;

public class AnnotationElementArray {
    public int numAnnotations;
    public AnnotationElement[] elements;


    public AnnotationElementArray(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        numAnnotations = dis.readUnsignedShort();
        elements = new AnnotationElement[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            elements[i] = new AnnotationElement();
            elements[i].read(dis);
        }
    }

}
