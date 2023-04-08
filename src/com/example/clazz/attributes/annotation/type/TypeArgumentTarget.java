package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeArgumentTarget {
    //    {
//        u2 offset;
//        u1 type_argument_index;
//    }
    public int offset;
    public int typeArgumentIndex;

    public TypeArgumentTarget(DataInputStream dis) throws IOException {
        offset = dis.readUnsignedShort();
        typeArgumentIndex = dis.readUnsignedByte();
    }


    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        DotItem typeArgumentTargetDot = new DotItem("type_argument_target", "")
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        typeArgumentTargetDot.addChild("offset", new DotItem("offset", offset + ""));
        typeArgumentTargetDot.addChild("type_argument_index", new DotItem("type_argument_index", typeArgumentIndex + ""));
        return typeArgumentTargetDot;
    }
}
