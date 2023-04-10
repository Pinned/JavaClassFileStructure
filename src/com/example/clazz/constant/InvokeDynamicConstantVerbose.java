package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

class InvokeDynamicConstantVerbose implements ConstantVerbose {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicConstantVerbose(DataInputStream dis) throws IOException {
        bootstrapMethodAttrIndex = dis.readUnsignedShort();
        nameAndTypeIndex = dis.readUnsignedShort();
    }

    @Override
    public void updateConstantIndex(DotItem item, ClassDot classDot) {
        item.addChild("bootstrapMethod", new DotItem("bootstrapMethodAttrIndex", "#" + bootstrapMethodAttrIndex, item));
        item.addChild("nameAndType", classDot.getConstantItem(nameAndTypeIndex));
    }

    @Override
    public int getSkipCount() {
        return 0;
    }
}
