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
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = InvokeDynamic\t#" + invokeDynamicIndex + ".#" + bootstrapMethodAttrIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n InvokeDynamic()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + invokeDynamicIndex + "[label=\"1.invokeDynamicIndex\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + bootstrapMethodAttrIndex + "[label=\"2.bootstrapMethodAttrIndex\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
