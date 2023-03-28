package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class NameAndTypeConstantVerbose implements ConstantVerbose {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
        descriptorIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
