package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class LongConstantVerbose implements ConstantVerbose {
    private long value;

    public LongConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readLong();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Long\t" + value);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Long(" + value + ")\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 1;
    }
}
