package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class AttributeVerboseFactory {
    public static AttributeVerbose createAttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants,
                                                          int attributeNameIndex, int attributeLength, DataInputStream dis) throws IOException {
        String attributeName = constants.get(String.valueOf(attributeNameIndex)).getValue();
        AttributeVerbose verbose = null;
        switch (attributeName) {
            case "Code":
                verbose = new CodeAttributeVerbose(parentTag, constants, attributeNameIndex);
                break;
            case "ConstantValue":
                verbose = new ConstantValueAttributeVerbose(parentTag, constants, attributeNameIndex);
                break;
//            case "Exceptions":
//                verbose = new ExceptionsAttributeVerbose(constants, attributeNameIndex);
//                break;
//            case "InnerClasses":
//                verbose = new InnerClassesAttributeVerbose(constants, attributeNameIndex);
//                break;
//            case "LineNumberTable":
//                verbose = new LineNumberTableAttributeVerbose(constants, attributeNameIndex);
//                break;
//            case "LocalVariableTable":
//                verbose = new LocalVariableTableAttributeVerbose(constants, attributeNameIndex);
//                break;
//            case "SourceFile":
//                verbose = new SourceFileAttributeVerbose(constants, attributeNameIndex);
//                break;
//            case "Synthetic":
//                verbose = new SyntheticAttributeVerbose(constants, attributeNameIndex);
//                break;
            default:
                verbose = new UnknownAttributeVerbose(parentTag, constants, attributeNameIndex);
                break;
        }
        verbose.readAttribute(attributeLength, dis);
        return verbose;

    }
}
