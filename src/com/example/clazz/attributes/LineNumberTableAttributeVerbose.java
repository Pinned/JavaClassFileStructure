package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class LineNumberTableAttributeVerbose extends AttributeVerbose {
    public int lineNumberTableLength;
    public LineNumberTableEntry[] lineNumberTable;

    public LineNumberTableAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        lineNumberTableLength = dis.readUnsignedShort();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry();
            lineNumberTable[i].startPc = dis.readUnsignedShort();
            lineNumberTable[i].lineNumber = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem lineNumberTableLengthDotItem = new DotItem("line_number_table_length", String.valueOf(lineNumberTableLength), superDotItem);
        superDotItem.addChild("line_number_table_length", lineNumberTableLengthDotItem);
        for (int i = 0; i < lineNumberTableLength; i++) {
            ArrayDotItem lineNumberTableEntryDotItem = new ArrayDotItem("line_number_table_entry", i, "")
                    .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
            superDotItem.addChild("line_number_table_entry", lineNumberTableEntryDotItem);
            DotItem startPcDotItem = new DotItem("start_pc", String.valueOf(lineNumberTable[i].startPc), lineNumberTableEntryDotItem);
            lineNumberTableEntryDotItem.addChild("start_pc", startPcDotItem);
            DotItem lineNumberDotItem = new DotItem("line_number", String.valueOf(lineNumberTable[i].lineNumber), lineNumberTableEntryDotItem);
            lineNumberTableEntryDotItem.addChild("line_number", lineNumberDotItem);
        }
        return superDotItem;
    }
}

class LineNumberTableEntry {
    public int startPc;
    public int lineNumber;

}

