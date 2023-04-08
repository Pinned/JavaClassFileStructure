package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class CatchTarget {
    //    {
//        u2 exception_table_index;
//    }
    public int exceptionTableIndex;

    public CatchTarget(DataInputStream dis) throws IOException {
        exceptionTableIndex = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("exception_table_index", exceptionTableIndex + "").parent(typeAnnotationDot);
    }
}
