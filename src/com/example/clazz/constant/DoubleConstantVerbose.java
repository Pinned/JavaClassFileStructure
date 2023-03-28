package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstantVerbose implements ConstantVerbose {
    private double value;

    public DoubleConstantVerbose(DataInputStream dis) throws IOException {
        value = dis.readDouble();
    }

    @Override
    public int getSkipCount() {
        return 1;
    }
}
