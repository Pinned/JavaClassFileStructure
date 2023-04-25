package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(nameIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
