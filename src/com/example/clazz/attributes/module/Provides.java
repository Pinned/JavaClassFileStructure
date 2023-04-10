package com.example.clazz.attributes.module;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class Provides {
    public int providesIndex;
    public int providesWithCount;
    public int[] providesWithIndex;

    public Provides(ClassDot classDot, DataInputStream dis) throws IOException {
        providesIndex = dis.readUnsignedShort();
        providesWithCount = dis.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = dis.readUnsignedShort();
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem providesDotItem, int i) {
        ArrayDotItem providesItem = new ArrayDotItem("provides", i, "")
                .parent(providesDotItem);
        providesItem.addChild("index", classDot.getConstantItem(providesIndex));
        providesItem.addChild("withCount", new DotItem("withCount", String.valueOf(providesWithCount), providesItem));
        for (int j = 0; j < providesWithCount; j++) {
            providesItem.addChild("withIndex", classDot.getConstantItem(providesWithIndex[j]));
        }
        return providesItem;
    }
}
