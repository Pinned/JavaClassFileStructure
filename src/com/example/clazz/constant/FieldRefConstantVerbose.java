package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class FieldRefConstantVerbose implements ConstantVerbose {
    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefConstantVerbose(DataInputStream dis) throws IOException {
        classIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Fieldref\t#" + classIndex + ".#" + nameAndTypeIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Fieldref()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + classIndex + "[label=\"1.fieldRef\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + nameAndTypeIndex + "[label=\"2.nameAndType\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
