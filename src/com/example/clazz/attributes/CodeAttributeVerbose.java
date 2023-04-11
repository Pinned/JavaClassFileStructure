package com.example.clazz.attributes;

import com.example.clazz.attributes.code.ExceptionTableEntry;
import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class CodeAttributeVerbose extends AttributeVerbose {

    public int maxStack;
    public int maxLocals;
    public int codeLength;
    public byte[] code;
    public int exceptionTableLength;
    public ExceptionTableEntry[] exceptionTable;
    public int attributesCount;
    public AttributeVerbose[] attributes;

    public CodeAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        maxStack = dis.readUnsignedShort();
        maxLocals = dis.readUnsignedShort();
        codeLength = dis.readInt();
        code = new byte[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = dis.readByte();
        }
        exceptionTableLength = dis.readUnsignedShort();
        exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry();
            exceptionTable[i].startPc = dis.readUnsignedShort();
            exceptionTable[i].endPc = dis.readUnsignedShort();
            exceptionTable[i].handlerPc = dis.readUnsignedShort();
            exceptionTable[i].catchType = dis.readUnsignedShort();
        }
        attributesCount = dis.readUnsignedShort();
        attributes = new AttributeVerbose[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeVerboseFactory.createAttributeVerbose(classDot, dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem maxStackDotItem = new DotItem("max_stack", String.valueOf(maxStack), superDotItem);
        superDotItem.addChild("max_stack", maxStackDotItem);
        DotItem maxLocalsDotItem = new DotItem("max_locals", String.valueOf(maxLocals), superDotItem);
        superDotItem.addChild("max_locals", maxLocalsDotItem);
        DotItem codeLengthDotItem = new DotItem("code_length", String.valueOf(codeLength), superDotItem);
        superDotItem.addChild("code_length", codeLengthDotItem);
        // 此处 Code 先只保存长度
        DotItem codeDotItem = new DotItem("code", String.valueOf(code.length), superDotItem);
        superDotItem.addChild("code", codeDotItem);
        DotItem exceptionTableLengthDotItem = new DotItem("exception_table_length", String.valueOf(exceptionTableLength), superDotItem);
        superDotItem.addChild("exception_table_length", exceptionTableLengthDotItem);
        for (int i = 0; i < exceptionTableLength; i++) {
            DotItem item = exceptionTable[i].createDotItem(classDot, superDotItem, i);
            superDotItem.addChild("", item);
        }
        DotItem attributesCountDotItem = new DotItem("attributes_count", String.valueOf(attributesCount), superDotItem);
        superDotItem.addChild("attributes_count", attributesCountDotItem);
        for (int i = 0; i < attributesCount; i++) {
            DotItem item = attributes[i].createDotItem(classDot, superDotItem, i);
            superDotItem.addChild("", item);
        }

        return superDotItem;
    }
}
