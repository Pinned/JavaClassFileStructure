package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class MethodRefConstantVerbose implements ConstantVerbose {
    private int methodRefIndex;
    private int nameAndTypeIndex;

    public MethodRefConstantVerbose(DataInputStream dis) throws IOException {
        methodRefIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Methodref\t#" + methodRefIndex + ".#" + nameAndTypeIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Methodref()\"]");
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
