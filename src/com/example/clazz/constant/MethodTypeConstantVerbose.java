package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class MethodTypeConstantVerbose implements ConstantVerbose {
    private int methodTypeIndex;

    public MethodTypeConstantVerbose(DataInputStream dis) throws IOException {
        methodTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(methodTypeIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
