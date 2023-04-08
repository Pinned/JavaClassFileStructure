package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeParameterBoundTarget {
    //    {
//        u1 type_parameter_index;
//        u1 bound_index;
//    }
    public int typeParameterIndex;
    public int boundIndex;

    public TypeParameterBoundTarget(DataInputStream dis) throws IOException {
        typeParameterIndex = dis.readUnsignedByte();
        boundIndex = dis.readUnsignedByte();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        DotItem item = new DotItem("type_parameter_bound_target", "")
                .parent(typeAnnotationDot)
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        item.addChild("type_parameter_index", new DotItem("type_parameter_index", typeParameterIndex + ""));
        item.addChild("bound_index", new DotItem("bound_index", boundIndex + ""));
        return item;
    }
}
