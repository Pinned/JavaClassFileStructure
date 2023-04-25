package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DynamicConstantVerbose implements ConstantVerbose {

    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;

    public DynamicConstantVerbose(DataInputStream dis) throws IOException {
        // 读取操作
        // 读取 Constant_dynamic_info
        bootstrapMethodAttrIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();

    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            // 写入操作
            // 写入 Constant_dynamic_info
            dos.writeByte(tag & 0xFF);
            dos.writeShort(bootstrapMethodAttrIndex & 0xFFFF);
            dos.writeShort(nameAndTypeIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
