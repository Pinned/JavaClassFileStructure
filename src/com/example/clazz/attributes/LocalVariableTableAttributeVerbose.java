package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTableAttributeVerbose extends AttributeVerbose{
    public int localVariableTableLength;
    public LocalVariableTableEntry[] localVariableTable;
    public LocalVariableTableAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        localVariableTableLength = dis.readUnsignedShort();
        localVariableTable = new LocalVariableTableEntry[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTable[i] = new LocalVariableTableEntry();
            localVariableTable[i].startPc = dis.readUnsignedShort();
            localVariableTable[i].length = dis.readUnsignedShort();
            localVariableTable[i].nameIndex = dis.readUnsignedShort();
            localVariableTable[i].descriptorIndex = dis.readUnsignedShort();
            localVariableTable[i].index = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem localVariableTableLengthDotItem = new DotItem("local_variable_table_length", String.valueOf(localVariableTableLength), superDotItem);
        superDotItem.addChild("table_length", localVariableTableLengthDotItem);
        for (int i = 0; i < localVariableTableLength; i++) {
            ArrayDotItem localVariableTableEntryDotItem = new ArrayDotItem("local_variable_table_entry", i, "")
                    .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
            superDotItem.addChild("entry", localVariableTableEntryDotItem);
            DotItem startPcDotItem = new DotItem("start_pc", String.valueOf(localVariableTable[i].startPc), localVariableTableEntryDotItem);
            localVariableTableEntryDotItem.addChild("start_pc", startPcDotItem);
            DotItem lengthDotItem = new DotItem("length", String.valueOf(localVariableTable[i].length), localVariableTableEntryDotItem);
            localVariableTableEntryDotItem.addChild("length", lengthDotItem);
            localVariableTableEntryDotItem.addChild("name", classDot.getConstantItem(localVariableTable[i].nameIndex));
            localVariableTableEntryDotItem.addChild("descriptor", classDot.getConstantItem(localVariableTable[i].descriptorIndex));
            DotItem indexDotItem = new DotItem("index", String.valueOf(localVariableTable[i].index), localVariableTableEntryDotItem);
            localVariableTableEntryDotItem.addChild("index", indexDotItem);
        }
        return superDotItem;
    }
}
class LocalVariableTableEntry {
    public int startPc;
    public int length;
    public int nameIndex;
    public int descriptorIndex;
    public int index;

}
