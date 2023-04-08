package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalvarTargetTable {
    //    {
//        u2 start_pc;
//        u2 length;
//        u2 index;
//    }
    public int startPc;
    public int length;
    public int index;

    public LocalvarTargetTable(DataInputStream dis) throws IOException {
        startPc = dis.readUnsignedShort();
        length = dis.readUnsignedShort();
        index = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int i) {
        ArrayDotItem localvarTargetTable = new ArrayDotItem("localvar_target_table", i, "")
                .parent(typeAnnotationDot)
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        localvarTargetTable.addChild("start_pc", new DotItem("start_pc", startPc + ""));
        localvarTargetTable.addChild("length", new DotItem("length", length + ""));
        localvarTargetTable.addChild("index", new DotItem("index", index + ""));
        return localvarTargetTable;
    }
}
