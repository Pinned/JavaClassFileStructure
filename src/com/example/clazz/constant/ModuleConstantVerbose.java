package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleConstantVerbose implements ConstantVerbose {
    private int nameIndex;

    public ModuleConstantVerbose(DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Module\t#" + nameIndex );
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Module()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + nameIndex + "[label=\"1.name\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
