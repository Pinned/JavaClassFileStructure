package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.AnnotationElement;
import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class RuntimeVisibleAnnotationsAttributeVerbose extends AttributeVerbose {
    int numAnnotations;
    AnnotationElement[] elements;

    public RuntimeVisibleAnnotationsAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        super(parentTag, constants, attributeNameIndex);
    }

    @Override
    public void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException {
        numAnnotations = dataInputStream.readUnsignedShort();
        elements = new AnnotationElement[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            elements[i] = new AnnotationElement();
            elements[i].read(dataInputStream, constants);
        }
    }

    @Override
    public void print(String parent, StringBuffer sb) {
        super.print(parent, sb);
        for (int i = 0; i < numAnnotations; i++) {
            elements[i].print(getCurrentNodeName(), i, sb);
            sb.append(getCurrentNodeName() + " -> " + getCurrentNodeName() + "_" + i + "[label=\"annotation" + i + "\"]");
        }

    }
}
