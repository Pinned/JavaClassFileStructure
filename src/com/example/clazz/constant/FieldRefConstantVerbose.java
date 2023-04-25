package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class FieldRefConstantVerbose implements ConstantVerbose {
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefConstantVerbose(DataInputStream dis) throws IOException {
        classIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(classIndex & 0xFFFF);
            dos.writeShort(nameAndTypeIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
