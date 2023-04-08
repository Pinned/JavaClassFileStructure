package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class SourceFileAttributeVerbose extends AttributeVerbose {
    public int sourceFileIndex;

    public SourceFileAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        sourceFileIndex = dis.readUnsignedShort();
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        superDotItem.addChild("source_file_index", classDot.getConstantItem(sourceFileIndex));
        return superDotItem;

    }
}
