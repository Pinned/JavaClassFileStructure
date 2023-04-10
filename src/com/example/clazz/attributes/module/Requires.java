package com.example.clazz.attributes.module;

import com.example.clazz.ModuleRequiresAccessFlagsUtil;
import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class Requires {
    public int requiresIndex;
    public int requiresFlags;
    public int requiresVersionIndex;

    public Requires(ClassDot classDot, DataInputStream dis) throws IOException {
        requiresIndex = dis.readUnsignedShort();
        requiresFlags = dis.readUnsignedShort();
        requiresVersionIndex = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, DotItem parent, int i) {
        ArrayDotItem requiresItem = new ArrayDotItem("requires", i, "")
                .parent(parent);
        requiresItem.addChild("index", classDot.getConstantItem(requiresIndex));
        requiresItem.addChild("flags", new DotItem("flags", ModuleRequiresAccessFlagsUtil.getAccessFlagDetail(requiresFlags), requiresItem));
        requiresItem.addChild("version", classDot.getConstantItem(requiresVersionIndex));
        return requiresItem;
    }
}
