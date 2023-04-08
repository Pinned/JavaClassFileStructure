package com.example.clazz.attributes.annotation;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.annotation.type.TypeAnnotationVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;


//{
//        u2              attribute_name_index;
//        u4              attribute_length;
//        u2              num_annotations;
//        type_annotation annotations[num_annotations];
//    }
public class TypeAnnotationAttributeVerbose extends AttributeVerbose {
    public int numAnnotations;
    public TypeAnnotationVerbose[] annotations;

    public TypeAnnotationAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        numAnnotations = dis.readUnsignedShort();
        annotations = new TypeAnnotationVerbose[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new TypeAnnotationVerbose(dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        superDotItem.addChild("num", new DotItem("num_annotations", String.valueOf(numAnnotations)));
        for (int i = 0; i < numAnnotations; i++) {
            DotItem item = annotations[i].createDotItem(classDot, superDotItem, i);
            superDotItem.addChild("", item);
        }
        return superDotItem;
    }
}
