package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class MethodHandleConstantVerbose implements ConstantVerbose {
    private int methodHandleKind;
    private int methodHandleIndex;

    public MethodHandleConstantVerbose(DataInputStream dis) throws IOException {
        methodHandleKind = dis.readUnsignedByte();
        methodHandleIndex = dis.readUnsignedShort();
    }

    @Override
    public void printInConsole(int index) {
        System.out.println("#" + index + " = MethodHandle\t" + methodHandleKind + ".#" + methodHandleIndex);
    }


    @Override
    public ConstantArrayDotItem createDotItem(int index, ClassDot classDot) {
        ConstantArrayDotItem item = new ConstantArrayDotItem(index, "MethodHandle(" + getMethodHandleKindReadableValue() + ")");
        item.constant = this;
        return item;
    }

    public String getMethodHandleKindReadableValue() {
        switch (methodHandleKind) {
            case 1:
                return "REF_getField";
            case 2:
                return "REF_getStatic";
            case 3:
                return "REF_putField";
            case 4:
                return "REF_putStatic";
            case 5:
                return "REF_invokeVirtual";
            case 6:
                return "REF_invokeStatic";
            case 7:
                return "REF_invokeSpecial";
            case 8:
                return "REF_newInvokeSpecial";
            case 9:
                return "REF_invokeInterface";
            default:
                return "Unknown";
        }
    }
    @Override
    public void updateConstantIndex(DotItem item, ClassDot classDot) {
        item.addChild("methodHandle", classDot.getConstantItem(methodHandleIndex));
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeByte(methodHandleKind & 0xFF);
            dos.writeShort(methodHandleIndex & 0xFFFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
