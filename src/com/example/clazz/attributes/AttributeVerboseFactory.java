package com.example.clazz.attributes;

import com.example.clazz.attributes.annotation.*;

import java.io.DataInputStream;
import java.io.IOException;

public class AttributeVerboseFactory {

    public static AttributeVerbose createAttributeVerbose(int attributeNameIndex, String name, DataInputStream dis) throws IOException {
        AttributeVerbose verbose = null;
        switch (name) {
            case "Code":
                verbose = new CodeAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "ConstantValue":
                verbose = new ConstantValueAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "Deprecated":
                verbose = new DeprecatedAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "RuntimeVisibleAnnotations":
                verbose = new RuntimeVisibleAnnotationsAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "RuntimeInvisibleAnnotations":
                verbose = new RuntimeInvisibleAnnotationsAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "RuntimeVisibleParameterAnnotations":
                verbose = new RuntimeVisibleParameterAnnotationsAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "RuntimeInvisibleParameterAnnotations":
                verbose = new RuntimeInvisibleParameterAnnotationsAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "AnnotationDefault":
                verbose = new AnnotationDefaultAttributeVerbose(attributeNameIndex, name, dis);
                break;
            case "Signature":
                verbose = new SignatureAttributeVerbose(attributeNameIndex, name, dis);
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
                verbose = new UnknownAttributeVerbose(attributeNameIndex, name, dis);
                break;
        }
        return verbose;
    }
}
