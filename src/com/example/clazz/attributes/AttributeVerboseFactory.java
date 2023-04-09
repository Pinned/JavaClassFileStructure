package com.example.clazz.attributes;

import com.example.clazz.dot.ClassDot;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class AttributeVerboseFactory {

    public static AttributeVerbose createAttributeVerbose(ClassDot dot, DataInputStream dis) throws IOException {
        int attributeNameIndex = dis.readUnsignedShort();
        String name = dot.getConstantItem(attributeNameIndex).constant.getValue();

        String className = name + "AttributeVerbose";
        try {
            Class clazz = Class.forName("com.example.clazz.attributes." + className);
            Constructor<AttributeVerbose> constructor = clazz.getConstructor(ClassDot.class, int.class, DataInputStream.class);
            constructor.setAccessible(true);
            return constructor.newInstance(dot, attributeNameIndex, dis);
        } catch (Exception e) {
            System.err.println("not found " + className);
            return new UnknownAttributeVerbose(dot, attributeNameIndex, dis);
        }
    }
}
