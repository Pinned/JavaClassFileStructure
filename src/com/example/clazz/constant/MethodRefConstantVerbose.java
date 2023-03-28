package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class MethodRefConstantVerbose implements ConstantVerbose {
    private int methodRefIndex;
    private int nameAndTypeIndex;

    public MethodRefConstantVerbose(DataInputStream dis) throws IOException {
        methodRefIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
