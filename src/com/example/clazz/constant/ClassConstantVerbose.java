package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassConstantVerbose implements ConstantVerbose {
    private int nameIndex;

    public ClassConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
