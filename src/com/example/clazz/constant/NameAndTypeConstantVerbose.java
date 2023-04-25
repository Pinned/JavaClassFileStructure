package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class NameAndTypeConstantVerbose implements ConstantVerbose {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
        descriptorIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(nameIndex & 0xFFFF);
            dos.writeShort(descriptorIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
