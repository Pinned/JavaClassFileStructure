package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class TypePath {
//    {
//        u1 path_length;
//        {   u1 type_path_kind;
//            u1 type_argument_index;
//        } path[path_length];
//    }
    public int pathLength;
    public TypePathEntry[] path;

    public TypePath(DataInputStream dis) throws IOException {
        pathLength = dis.readUnsignedByte();
        path = new TypePathEntry[pathLength];
        for (int i = 0; i < pathLength; i++) {
            path[i] = new TypePathEntry(dis);
        }
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        DotItem typePathDot = new DotItem("type_path", "")
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        typePathDot.addChild("path_length", new DotItem("path_length", pathLength + ""));
        for (int j = 0; j < pathLength; j++) {
            typePathDot.addChild("path[" + j + "]", path[j].createDotItem(classDot, typeAnnotationDot, j));
        }
        return typePathDot;
    }
}
