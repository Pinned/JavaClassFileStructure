package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

class Utf8ConstantVerbose implements ConstantVerbose {
    private String value;

    public Utf8ConstantVerbose(DataInputStream dis) throws IOException {
        int length = dis.readUnsignedShort();
        byte[] bytes = new byte[length];
        dis.read(bytes);
        value = new String(bytes);
    }

    @Override
    public void print(int index, StringBuffer sb) {
        System.out.println("#" + index + " = Utf8\t" + value);
        sb.append("constant_item_" + index + "[label=\"" + index + "\\n Utf8(" + value + ")\"]");
        sb.append(";\n");
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
