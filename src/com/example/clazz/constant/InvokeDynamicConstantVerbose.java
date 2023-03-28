package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class InvokeDynamicConstantVerbose implements ConstantVerbose {
    private int invokeDynamicIndex;
    private int bootstrapMethodAttrIndex;

    public InvokeDynamicConstantVerbose(DataInputStream dis) throws IOException {
        invokeDynamicIndex = dis.readUnsignedShort();
        bootstrapMethodAttrIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
