package com.example.clazz.constant;

import java.io.DataInputStream;
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
}
