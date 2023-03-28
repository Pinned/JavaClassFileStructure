package com.example.clazz.constant;

import java.io.DataInputStream;
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
}
