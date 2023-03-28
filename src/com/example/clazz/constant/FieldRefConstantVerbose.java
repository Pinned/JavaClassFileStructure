package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class FieldRefConstantVerbose implements ConstantVerbose {
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefConstantVerbose(DataInputStream dis) throws IOException {
        classIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
