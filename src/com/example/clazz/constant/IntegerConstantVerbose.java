package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class IntegerConstantVerbose implements ConstantVerbose {
    private int value;

    public IntegerConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readInt();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Integer\t" + value);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Integer(" + value + ")\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
