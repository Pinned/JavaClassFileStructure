package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class SupertypeTarget {
    //    {
//        u2 supertype_index;
//    }
    public int supertypeIndex;

    public SupertypeTarget(DataInputStream dis) throws IOException {
        supertypeIndex = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("supertype_index", supertypeIndex + "").parent(typeAnnotationDot);
    }
}
