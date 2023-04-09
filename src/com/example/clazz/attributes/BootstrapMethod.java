package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class BootstrapMethod {
    public int bootstrapMethodRef;
    public int numBootstrapArguments;
    public int[] bootstrapArguments;

    public BootstrapMethod(ClassDot classDot, DataInputStream dis) throws IOException {
        bootstrapMethodRef = dis.readUnsignedShort();
        numBootstrapArguments = dis.readUnsignedShort();
        bootstrapArguments = new int[numBootstrapArguments];
        for (int i = 0; i < numBootstrapArguments; i++) {
            bootstrapArguments[i] = dis.readUnsignedShort();
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem superDotItem, int index) {
        ArrayDotItem dotItem = new ArrayDotItem("bootstrapMethod", index, "")
                .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
//        DotItem bootstrapMethodRefDotItem = new DotItem("bootstrapMethodRef", String.valueOf(this.bootstrapMethodRef))
//                .parent(dotItem);
        DotItem numBootstrapArgumentsDotItem = new DotItem("numBootstrapArguments", String.valueOf(this.numBootstrapArguments))
                .parent(dotItem);
        dotItem.addChild("bootstrapMethodRef", classDot.getConstantItem(bootstrapMethodRef));
        dotItem.addChild("numBootstrapArguments", numBootstrapArgumentsDotItem);
        for (int j = 0; j < numBootstrapArguments; j++) {
            ArrayDotItem bootstrapArgument = new ArrayDotItem("bootstrapArgument", j, "")
                    .parent(dotItem);
            bootstrapArgument.addChild("boot_arg" + j, classDot.getConstantItem(bootstrapArguments[j]));
            dotItem.addChild("boot_arg" + j, bootstrapArgument);
        }
        return dotItem;
    }
}
