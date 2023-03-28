package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class InterfaceMethodRefConstantVerbose implements ConstantVerbose {
    private int methodRefIndex;
    private int nameAndTypeIndex;

    public InterfaceMethodRefConstantVerbose(DataInputStream dis) throws IOException {
        methodRefIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = InterfaceMethodref\t#" + methodRefIndex + ".#" + nameAndTypeIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n InterfaceMethodref()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + methodRefIndex + "[label=\"1.methodRef\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + nameAndTypeIndex + "[label=\"2.nameAndType\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
