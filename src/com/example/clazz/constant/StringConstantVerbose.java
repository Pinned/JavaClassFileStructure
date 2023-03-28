package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class StringConstantVerbose implements ConstantVerbose {
    private int index;

    public StringConstantVerbose(DataInputStream dis) throws IOException {
        index = dis.readUnsignedShort();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = String\t#" + this.index);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n String()\"]");
        sb.append(";\n");
        sb.append("constant_item_" + index + " -> constant_item_" + this.index);
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
