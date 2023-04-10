package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodParametersAttributeVerbose extends AttributeVerbose{
    public int parametersCount;
    public MethodParameter[] parameters;
    public MethodParametersAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        System.out.println("MethodParametersAttributeVerbose");
        parametersCount = dis.readUnsignedByte();
        parameters = new MethodParameter[parametersCount];
        for (int i = 0; i < parametersCount; i++) {
            parameters[i] = new MethodParameter(classDot, dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        DotItem dotItem = new DotItem("parameters_count", parametersCount + "")
                .parent(superDotItem);
        superDotItem.addChild("num", dotItem);
        for (int i = 0; i < parametersCount; i++) {
            superDotItem.addChild("param_" + i, parameters[i].createDotItem(classDot, superDotItem, i));
        }
        return superDotItem;
    }
}
