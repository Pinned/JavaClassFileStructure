package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class LongConstantVerbose implements ConstantVerbose {
    private long value;

    public LongConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readLong();
    }
    
    @Override
    public int getSkipCount() {
        return 1;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeLong(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
