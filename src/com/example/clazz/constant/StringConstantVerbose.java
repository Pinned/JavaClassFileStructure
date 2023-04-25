package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class StringConstantVerbose implements ConstantVerbose {
    private int valueIndex;

    public StringConstantVerbose(DataInputStream dis) throws IOException {
        valueIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(valueIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
