package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstantVerbose implements ConstantVerbose {
    private double value;

    public DoubleConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readDouble();
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Double\t" + value);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Double(" + value + ")\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 1;
    }
}
