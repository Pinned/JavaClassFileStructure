//package com.example.clazz;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//
//public class AttributeParserReader {
//    public static void parseAttributeInfo(StringBuffer sb, DataInputStream dis, int attributeLength) throws IOException {
//        // 读取 attribute_name_index
//        // 读取 attribute_name_index
//        int attributeNameIndex = dis.readUnsignedShort();
//        sb.append("\n\tattribute_name_index: ").append(attributeNameIndex);
//
//        // 读取 attribute_length
//        int attributeLength = dis.readInt();
//        sb.append("\n\tattribute_length: ").append(attributeLength);
//
//        // 读取 info
//        byte[] info = new byte[attributeLength];
//        dis.readFully(info);
//        sb.append("\n\tinfo: ").append(Arrays.toString(info));
//        switch (attributeNameIndex) {
//            case AttributeType.CONSTANT_VALUE:
//                ConstantValueAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.CODE:
//                CodeAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.EXCEPTIONS:
//                ExceptionsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.LINE_NUMBER_TABLE:
//                LineNumberTableAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.LOCAL_VARIABLE_TABLE:
//                LocalVariableTableAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.INNER_CLASSES:
//                InnerClassesAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.SOURCE_FILE:
//                SourceFileAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.DEPRECATED:
//                DeprecatedAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.RUNTIME_VISIBLE_ANNOTATIONS:
//                RuntimeVisibleAnnotationsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.RUNTIME_INVISIBLE_ANNOTATIONS:
//                RuntimeInvisibleAnnotationsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
//                RuntimeVisibleParameterAnnotationsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
//                RuntimeInvisibleParameterAnnotationsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.ANNOTATION_DEFAULT:
//                AnnotationDefaultAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.BOOTSTRAP_METHODS:
//                BootstrapMethodsAttributeParser.parse(sb, dis);
//                break;
//            case AttributeType.METHOD_PARAMETERS:
//                MethodParametersAttributeParser.parse(sb, dis);
//                break;
//            default:
//                sb.append("\n\tunknown attribute");
//                break;
//        }
//
//    }
//}
