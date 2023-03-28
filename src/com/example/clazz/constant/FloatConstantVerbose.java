package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class FloatConstantVerbose implements ConstantVerbose {
    private float value;

    public FloatConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readFloat();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Float\t" + value);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Float(" + value + ")\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
