package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class BootstrapMethodsAttributeVerbose extends AttributeVerbose {
    public int numberOfBootstrapMethods;
    public BootstrapMethod[] bootstrapMethods;

    public BootstrapMethodsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        numberOfBootstrapMethods = dis.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[numberOfBootstrapMethods];
        for (int i = 0; i < numberOfBootstrapMethods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(classDot, dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem number = new DotItem("number", String.valueOf(numberOfBootstrapMethods))
                .parent(superDotItem);
        superDotItem.addChild("num", number);
        for (int i = 0; i < numberOfBootstrapMethods; i++) {
            superDotItem.addChild("", bootstrapMethods[i].createDotItem(classDot, superDotItem, i));
        }
        return superDotItem;
    }
}
