package com.example.clazz.constant;

import java.io.DataInputStream;
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
}
