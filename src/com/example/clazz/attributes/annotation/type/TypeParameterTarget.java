package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeParameterTarget {
//    {
//        u1 type_parameter_index;
//    }
    public int typeParameterIndex;
    public TypeParameterTarget(DataInputStream dis) throws IOException {
        typeParameterIndex = dis.readUnsignedByte();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("type_parameter_index", typeParameterIndex + "").parent(typeAnnotationDot);
    }
}
