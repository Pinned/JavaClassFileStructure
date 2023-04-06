package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

class MethodHandleConstantVerbose implements ConstantVerbose {
    private int methodHandleKind;
    private int methodHandleIndex;

    public MethodHandleConstantVerbose(DataInputStream dis) throws IOException {
        methodHandleKind = dis.readUnsignedByte();
        methodHandleIndex = dis.readUnsignedShort();
    }

    @Override
    public ConstantArrayDotItem createDotItem(int index, ClassDot classDot) {
        ConstantArrayDotItem item = new ConstantArrayDotItem(index, "MethodHandle(" + methodHandleKind + ")");
        item.addChild("methodHandle", classDot.getConstantItem(methodHandleIndex));
        return item;
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
