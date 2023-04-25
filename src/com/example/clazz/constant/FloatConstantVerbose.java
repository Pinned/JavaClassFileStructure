package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class FloatConstantVerbose implements ConstantVerbose {
    private float value;

    public FloatConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readFloat();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeFloat(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
