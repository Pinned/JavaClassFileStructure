package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class FormalParameterTarget {
    //    {
//        u1 formal_parameter_index;
//    }
    public int formalParameterIndex;

    public FormalParameterTarget(DataInputStream dis) throws IOException {
        this.formalParameterIndex = dis.readUnsignedByte();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        return new DotItem("formal_parameter_index", formalParameterIndex + "");
    }
}
