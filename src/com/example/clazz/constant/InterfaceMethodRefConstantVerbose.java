package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class InterfaceMethodRefConstantVerbose implements ConstantVerbose {
    private int methodRefIndex;
    private int nameAndTypeIndex;

    public InterfaceMethodRefConstantVerbose(DataInputStream dis) throws IOException {
        methodRefIndex = dis.readUnsignedShort();
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
            dos.writeShort(methodRefIndex & 0xFFFF);
            dos.writeShort(nameAndTypeIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
