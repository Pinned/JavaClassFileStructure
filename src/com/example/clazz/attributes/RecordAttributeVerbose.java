package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class RecordAttributeVerbose extends AttributeVerbose {
    public int componentsCount;
    public RecordComponentInfo[] components;

    public RecordAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        componentsCount = dis.readUnsignedShort();
        components = new RecordComponentInfo[componentsCount];
        for (int i = 0; i < componentsCount; i++) {
            components[i] = new RecordComponentInfo(classDot, dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        for (int i = 0; i < componentsCount; i++) {
            DotItem item = components[i].createDotItem(classDot, superDotItem, i);
            superDotItem.addChild("", item);
        }
        return superDotItem;
    }
}
