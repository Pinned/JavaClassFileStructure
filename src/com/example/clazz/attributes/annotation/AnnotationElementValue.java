package com.example.clazz.attributes.annotation;

import com.example.clazz.constant.ConstantVerbose;

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

    public void read(DataInputStream dis, Map<String, ConstantVerbose> constants) throws IOException {
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
                annotationValue.read(dis, constants);
                break;
            case '[':
                numValues = dis.readUnsignedShort();
                values = new AnnotationElementValue[numValues];
                for (int i = 0; i < numValues; i++) {
                    values[i] = new AnnotationElementValue();
                    values[i].read(dis, constants);
                }
                break;
            default:
                // 跳过对应字节数
                dis.skipBytes(2);
        }

    }

    public void print(String parent, StringBuffer sb) {
        String currentNodeName = parent + "_value";
        sb.append(currentNodeName + "[label=\"value\"]");
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
                sb.append(currentNodeName + " -> constant_item_" + constValueIndex + "[label=\"constValue\"]");
                break;
            case 'e':
                sb.append(currentNodeName + " -> constant_item_" + typeNameIndex + "[label=\"typeName\"]");
                sb.append(currentNodeName + " -> constant_item_" + constNameIndex + "[label=\"constName\"]");
                break;
            case 'c':
                sb.append(currentNodeName + " -> constant_item_" + classInfoIndex + "[label=\"classInfo\"]");
                break;
            case '@':
                annotationValue.print(currentNodeName, 0, sb);
                sb.append(currentNodeName + " -> " + currentNodeName + "_annotationValue[label=\"annotationValue\"]");
                break;
            case '[':
                for (int i = 0; i < numValues; i++) {
                    values[i].print(currentNodeName, sb);
                    sb.append(currentNodeName + " -> " + currentNodeName + "_" + i + "[label=\"value" + i + "\"]");
                }
                break;
            default:
                // 跳过对应字节数
                break;
        }
    }
}
