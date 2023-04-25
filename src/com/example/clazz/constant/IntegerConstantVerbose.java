package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class IntegerConstantVerbose implements ConstantVerbose {
    private int value;

    public IntegerConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readInt();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeInt(value & 0xFFFFFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
