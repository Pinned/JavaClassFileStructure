package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClassConstantVerbose implements ConstantVerbose {
    private int nameIndex;

    public ClassConstantVerbose(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public ClassConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
