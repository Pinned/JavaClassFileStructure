package com.example.clazz.constant;

import java.io.DataInputStream;
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
    public void print(int index, StringBuffer sb) {
        sb.append("c" + index + " [label=\"Dynamic\"];\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
