package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class MethodTypeConstantVerbose implements ConstantVerbose {
    private int methodTypeIndex;

    public MethodTypeConstantVerbose(DataInputStream dis) throws IOException {
        methodTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = MethodType\t#" + methodTypeIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n MethodType()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + methodTypeIndex + "[label=\"1.methodType\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
