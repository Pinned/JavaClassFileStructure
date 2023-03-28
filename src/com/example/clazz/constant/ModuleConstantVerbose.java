package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleConstantVerbose implements ConstantVerbose {
    private int nameIndex;

    public ModuleConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
