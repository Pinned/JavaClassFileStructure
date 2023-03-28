package com.example.clazz.constant;

import java.io.DataInputStream;
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
}
