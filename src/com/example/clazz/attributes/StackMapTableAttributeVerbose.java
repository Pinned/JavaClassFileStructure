package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;

public class StackMapTableAttributeVerbose extends AttributeVerbose {
    public int numberOfEntries;
    public StackMapFrame[] entries;

    public StackMapTableAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        numberOfEntries = dis.readUnsignedShort();
        entries = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            entries[i] = new StackMapFrame();
            entries[i].frameType = dis.readUnsignedByte();
            if (entries[i].frameType >= 0 && entries[i].frameType <= 63) {
                // same_frame
            } else if (entries[i].frameType >= 64 && entries[i].frameType <= 127) {
                // same_locals_1_stack_item_frame
                entries[i].numberOfStackItems = 1;
                entries[i].stack = new VerificationTypeInfo[entries[i].numberOfStackItems];
                entries[i].stack[0] = new VerificationTypeInfo();
                entries[i].stack[0].tag = dis.readUnsignedByte();
                if (entries[i].stack[0].tag == 7) {
                    entries[i].stack[0].cpoolIndex = dis.readUnsignedShort();
                } else if (entries[i].stack[0].tag == 8) {
                    entries[i].stack[0].offset = dis.readUnsignedShort();
                }
            } else if (entries[i].frameType == 247) {
                // same_locals_1_stack_item_frame_extended
                entries[i].offsetDelta = dis.readUnsignedShort();
                entries[i].numberOfStackItems = 1;
                entries[i].stack = new VerificationTypeInfo[entries[i].numberOfStackItems];
                entries[i].stack[0] = new VerificationTypeInfo();
                entries[i].stack[0].tag = dis.readUnsignedByte();
                if (entries[i].stack[0].tag == 7) {
                    entries[i].stack[0].cpoolIndex = dis.readUnsignedShort();
                } else if (entries[i].stack[0].tag == 8) {
                    entries[i].stack[0].offset = dis.readUnsignedShort();
                }
            } else if (entries[i].frameType >= 248 && entries[i].frameType <= 250) {
                // chop_frame
                entries[i].offsetDelta = dis.readUnsignedShort();
            } else if (entries[i].frameType == 251) {
                // same_frame_extended
                entries[i].offsetDelta = dis.readUnsignedShort();
            } else if (entries[i].frameType >= 252 && entries[i].frameType <= 254) {
                // append_frame
                entries[i].offsetDelta = dis.readUnsignedShort();
                entries[i].numberOfLocals = entries[i].frameType - 251;
                entries[i].locals = new VerificationTypeInfo[entries[i].numberOfLocals];
                for (int j = 0; j < entries[i].numberOfLocals; j++) {
                    entries[i].locals[j] = new VerificationTypeInfo();
                    entries[i].locals[j].tag = dis.readUnsignedByte();
                    if (entries[i].locals[j].tag == 7) {
                        entries[i].locals[j].cpoolIndex = dis.readUnsignedShort();
                    } else if (entries[i].locals[j].tag == 8) {
                        entries[i].locals[j].offset = dis.readUnsignedShort();
                    }
                }
            } else if (entries[i].frameType == 255) {
                // full_frame
                entries[i].offsetDelta = dis.readUnsignedShort();
                entries[i].numberOfLocals = dis.readUnsignedShort();
                entries[i].locals = new VerificationTypeInfo[entries[i].numberOfLocals];
                for (int j = 0; j < entries[i].numberOfLocals; j++) {
                    entries[i].locals[j] = new VerificationTypeInfo();
                    entries[i].locals[j].tag = dis.readUnsignedByte();
                    if (entries[i].locals[j].tag == 7) {
                        entries[i].locals[j].cpoolIndex = dis.readUnsignedShort();
                    } else if (entries[i].locals[j].tag == 8) {
                        entries[i].locals[j].offset = dis.readUnsignedShort();
                    }
                }
                entries[i].numberOfStackItems = dis.readUnsignedShort();
                entries[i].stack = new VerificationTypeInfo[entries[i].numberOfStackItems];
                for (int j = 0; j < entries[i].numberOfStackItems; j++) {
                    entries[i].stack[j] = new VerificationTypeInfo();
                    entries[i].stack[j].tag = dis.readUnsignedByte();
                    if (entries[i].stack[j].tag == 7) {
                        entries[i].stack[j].cpoolIndex = dis.readUnsignedShort();
                    } else if (entries[i].stack[j].tag == 8) {
                        entries[i].stack[j].offset = dis.readUnsignedShort();
                    }
                }

            }
        }

    }

    private class StackMapFrame {
        public int frameType;
        public int offsetDelta;
        public int numberOfLocals;
        public VerificationTypeInfo[] locals;
        public int numberOfStackItems;
        public VerificationTypeInfo[] stack;

    }

    private class VerificationTypeInfo {
        public int tag;
        public int cpoolIndex;
        public int offset;
    }
}
