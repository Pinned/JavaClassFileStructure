package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(bootstrapMethodAttrIndex & 0xFFFF);
            dos.writeShort(nameAndTypeIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
