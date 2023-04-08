package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class OffsetTarget {
    //    {
//        u2 offset;
//    }
    public int offset;

    public OffsetTarget(DataInputStream dis) throws IOException {
        offset = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("offset", offset + "").parent(typeAnnotationDot);
    }
}
