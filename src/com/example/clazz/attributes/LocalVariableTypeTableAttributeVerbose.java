package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTypeTableAttributeVerbose extends AttributeVerbose {


    public int localVariableTypeTableLength;
    public LocalVariableTypeTableEntry[] localVariableTypeTable;

    public LocalVariableTypeTableAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        localVariableTypeTableLength = dis.readUnsignedShort();
        localVariableTypeTable = new LocalVariableTypeTableEntry[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariableTypeTable[i] = new LocalVariableTypeTableEntry();
            localVariableTypeTable[i].startPc = dis.readUnsignedShort();
            localVariableTypeTable[i].length = dis.readUnsignedShort();
            localVariableTypeTable[i].nameIndex = dis.readUnsignedShort();
            localVariableTypeTable[i].signatureIndex = dis.readUnsignedShort();
            localVariableTypeTable[i].index = dis.readUnsignedShort();
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem localVariableTypeTableLengthDotItem = new DotItem("local_variable_type_table_length", String.valueOf(localVariableTypeTableLength), superDotItem);
        superDotItem.addChild("table_length", localVariableTypeTableLengthDotItem);
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            ArrayDotItem localVariableTypeTableEntryDotItem = new ArrayDotItem("local_variable_type_table_entry", i, "")
                    .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
            superDotItem.addChild("entry", localVariableTypeTableEntryDotItem);
            DotItem startPcDotItem = new DotItem("start_pc", String.valueOf(localVariableTypeTable[i].startPc), localVariableTypeTableEntryDotItem);
            localVariableTypeTableEntryDotItem.addChild("start_pc", startPcDotItem);
            DotItem lengthDotItem = new DotItem("length", String.valueOf(localVariableTypeTable[i].length), localVariableTypeTableEntryDotItem);
            localVariableTypeTableEntryDotItem.addChild("length", lengthDotItem);
            localVariableTypeTableEntryDotItem.addChild("name", classDot.getConstantItem(localVariableTypeTable[i].nameIndex));
            localVariableTypeTableEntryDotItem.addChild("signature", classDot.getConstantItem(localVariableTypeTable[i].signatureIndex));
            DotItem indexDotItem = new DotItem("index", String.valueOf(localVariableTypeTable[i].index), localVariableTypeTableEntryDotItem);
            localVariableTypeTableEntryDotItem.addChild("index", indexDotItem);
        }
        return superDotItem;
    }

    private class LocalVariableTypeTableEntry {
        public int startPc;
        public int length;
        public int nameIndex;
        public int signatureIndex;
        public int index;
    }
}
