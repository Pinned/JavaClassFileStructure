package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class MethodHandleConstantVerbose implements ConstantVerbose {
    private int methodHandleKind;
    private int methodHandleIndex;

    public MethodHandleConstantVerbose(DataInputStream dis) throws IOException {
        methodHandleKind = dis.readUnsignedByte();
        methodHandleIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = MethodHandle\t" + methodHandleKind + ".#" + methodHandleIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n MethodHandle(" + methodHandleKind + ")\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + methodHandleIndex + "[label=\"index\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
