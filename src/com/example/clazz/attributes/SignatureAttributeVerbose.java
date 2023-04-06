package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class SignatureAttributeVerbose extends AttributeVerbose {
    public int signatureIndex;

    public SignatureAttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        super(attributeNameIndex, attributeName, dis);
        signatureIndex = dis.readUnsignedShort();
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superItem = super.createDotItem(classDot, parent, index);
        superItem.addChild("signature", classDot.getConstantItem(signatureIndex));
        return superItem;
    }
}
