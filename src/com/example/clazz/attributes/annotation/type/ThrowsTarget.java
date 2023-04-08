package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class ThrowsTarget {
    //    {
//        u2 throws_type_index;
//    }
    public int throwsTypeIndex;

    public ThrowsTarget(DataInputStream dis) throws IOException {
        throwsTypeIndex = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("throws_type_index", throwsTypeIndex + "");
    }
}
