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
        ArrayDotItem typePathEntryDot = new ArrayDotItem("type_path", j, "")
                .parent(typeAnnotationDot).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        typePathEntryDot.addChild("type_path_kind", new DotItem("type_path_kind", getTypePathKindReadableValue())
                .parent(typePathEntryDot).shape(DotShape.BOX));
        typePathEntryDot.addChild("type_argument_index", new DotItem("type_argument_index", typeArgumentIndex + "")
                .parent(typePathEntryDot));
        return typePathEntryDot;
    }

    public String getTypePathKindReadableValue() {
        switch (typePathKind) {
            case 0:
                return "type argument of a parameterized type is the wildcard ?";
            case 1:
                return "type argument of a parameterized type is an array type";
            case 2:
                return "type argument of a parameterized type is a nested type";
            case 3:
                return "type argument of a parameterized type is a type variable";
            case 4:
                return "type argument of a parameterized type is a wildcard with additional bound information";
            default:
                return "unknown";
        }
    }
}
