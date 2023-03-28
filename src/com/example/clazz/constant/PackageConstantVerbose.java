package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class PackageConstantVerbose implements ConstantVerbose {
    private int nameIndex;

    public PackageConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Package()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + nameIndex + "[label=\"1.name\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
