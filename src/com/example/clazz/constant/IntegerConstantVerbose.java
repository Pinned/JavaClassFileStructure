package com.example.clazz.constant;

import java.io.DataInputStream;
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
}
