package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class NameAndTypeConstantVerbose implements ConstantVerbose {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
        descriptorIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = NameAndType\t#" + nameIndex + ".#" + descriptorIndex);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n NameAndType()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + nameIndex + "[label=\"1.name\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + descriptorIndex + "[label=\"2.descriptor\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
