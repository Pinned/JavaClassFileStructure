package com.example.clazz.attributes.annotation.type;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalvarTarget {
    //    {
//        u2 table_length;
//        {
//            u2 start_pc;
//            u2 length;
//            u2 index;
//        } table[table_length];
//    }
    public int tableLength;
    public LocalvarTargetTable[] table;

    public LocalvarTarget(DataInputStream dis) throws IOException {
        tableLength = dis.readUnsignedShort();
        table = new LocalvarTargetTable[tableLength];
        for (int i = 0; i < tableLength; i++) {
            table[i] = new LocalvarTargetTable(dis);
        }
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem typeAnnotationDot, int index) {
        DotItem item = new DotItem("localvar_target", "")
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        item.addChild("table_length", new DotItem("table_length", tableLength + ""));
        for (int i = 0; i < tableLength; i++) {
            item.addChild("table[" + i + "]", table[i].createDotItem(classDot, typeAnnotationDot, i));
        }
        return item;
    }
}
