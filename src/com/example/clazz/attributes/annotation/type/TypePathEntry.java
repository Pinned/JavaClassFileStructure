package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class TypePathEntry {
    //        {   u1 type_path_kind;
//            u1 type_argument_index;
//        }
    public int typePathKind;
    public int typeArgumentIndex;

    public TypePathEntry(DataInputStream dis) throws IOException {
        typePathKind = dis.readUnsignedByte();
        typeArgumentIndex = dis.readUnsignedByte();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int j) {
        DotItem typePathEntryDot = new DotItem("type_path", "")
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        typePathEntryDot.addChild("type_path_kind", new DotItem("type_path_kind", typePathKind + ""));
        typePathEntryDot.addChild("type_argument_index", new DotItem("type_argument_index", typeArgumentIndex + ""));
        return typePathEntryDot;
    }
}
