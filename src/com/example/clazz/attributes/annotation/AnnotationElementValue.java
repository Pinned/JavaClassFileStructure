package com.example.clazz.attributes.annotation;

import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class AnnotationElementValue {
    //    element_value {
//        u1 tag;
//        union {
//            u2 const_value_index;
//
//            {   u2 type_name_index;
//                u2 const_name_index;
//            } enum_const_value;
//
//            u2 class_info_index;
//
//            annotation annotation_value;
//
//            {   u2            num_values;
//                element_value values[num_values];
//            } array_value;
//        } value;
//    }
    public byte tag;
    public int constValueIndex;
    public int typeNameIndex;
    public int constNameIndex;
    public int classInfoIndex;
    public AnnotationElement annotationValue;
    public int numValues;
    public AnnotationElementValue[] values;

    public void read(DataInputStream dis) throws IOException {
        tag = dis.readByte();
        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                constValueIndex = dis.readUnsignedShort();
                break;
            case 'e':
                typeNameIndex = dis.readUnsignedShort();
                constNameIndex = dis.readUnsignedShort();
                break;
            case 'c':
                classInfoIndex = dis.readUnsignedShort();
                break;
            case '@':
                annotationValue = new AnnotationElement();
                annotationValue.read(dis);
                break;
            case '[':
                numValues = dis.readUnsignedShort();
                values = new AnnotationElementValue[numValues];
                for (int i = 0; i < numValues; i++) {
                    values[i] = new AnnotationElementValue();
                    values[i].read(dis);
                }
                break;
            default:
                // 跳过对应字节数
                dis.skipBytes(2);
        }

    }

    public void print(String parent, StringBuffer sb) {

    }

    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        ArrayDotItem item = new ArrayDotItem("value", index, "value")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        item.addChild("tag", new DotItem("tag", "" + ((char) tag), item));
        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                item.addChild("constValue", classDot.getConstantItem(constValueIndex));
                break;
            case 'e':
                item.addChild("typeName", classDot.getConstantItem(typeNameIndex));
                item.addChild("constName", classDot.getConstantItem(constNameIndex));
                break;
            case 'c':
                item.addChild("classInfo", classDot.getConstantItem(classInfoIndex));
                break;
            case '@':
                DotItem annotationItem = annotationValue.createDotItem(classDot, item, 0);
                item.addChild("annotationValue", annotationItem);
                break;
            case '[':
                for (int i = 0; i < numValues; i++) {
                    DotItem innerItem = values[i].createDotItem(classDot, item, i);
                    item.addChild("value_" + i, innerItem);
                }
                break;
            default:
                // 跳过对应字节数
                break;
        }
        return item;
    }
}
