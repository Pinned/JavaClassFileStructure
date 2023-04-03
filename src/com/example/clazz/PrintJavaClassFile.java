package com.example.clazz;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.AttributeVerboseFactory;
import com.example.clazz.constant.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PrintJavaClassFile {
    public static void main(String[] args) throws IOException {
        // 获取当前项目的根目录
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前项目的根目录：" + projectPath);
        File file = new File(projectPath, "class_info");
        for (File listFile : file.listFiles()) {
            if (listFile.isFile() && listFile.getName().endsWith(".class")) {
                parseClassFile(projectPath, listFile.getAbsolutePath());
            }
        }
    }

    private static void parseClassFile(String projectPath, String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fis);
        int magic = dis.readInt();
        int minorVersion = dis.readUnsignedShort();
        int majorVersion = dis.readUnsignedShort();
        int constantPoolCount = dis.readUnsignedShort();
        System.out.println("魔数：" + Integer.toHexString(magic));
        System.out.println("次版本号：" + minorVersion);
        System.out.println("主版本号：" + majorVersion);
        System.out.println("常量池数量：" + constantPoolCount);
        Map<String, ConstantVerbose> allConstant = new HashMap<>();
        StringBuffer sb = new StringBuffer("digraph constant_pool { \n");
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = dis.readUnsignedByte();
            ConstantVerbose constantVerbose = ConstantVerboseFactory.createConstant(tag, dis);
            constantVerbose.print(i, sb);
            i += constantVerbose.getSkipCount();
            allConstant.put(String.valueOf(i), constantVerbose);
        }

        // 读取 access flag \ this class \ super class
        int classAccessFlags = dis.readUnsignedShort();
        System.out.println("访问标志：" + ClassAccessFlagsUtil.getAccessFlagDetail(classAccessFlags));
        int thisClass = dis.readUnsignedShort();
        System.out.println("this class：#" + thisClass);
        sb.append("this_class [label=\"this_class\", color=blue, shape = doublecircle];\n");
        sb.append("this_class -> constant_item_" + thisClass + ";\n");
        int superClass = dis.readUnsignedShort();
        System.out.println("super class：#" + superClass);
        sb.append("super_class [label=\"super_class\", color=blue, shape=doublecircle];\n");
        sb.append("super_class -> constant_item_" + superClass + ";\n");
        int interfaceCount = dis.readUnsignedShort();
        System.out.println("接口数量：" + interfaceCount);
        for (int i = 0; i < interfaceCount; i++) {
            int interfaceIndex = dis.readUnsignedShort();
            System.out.println("接口" + i + "的索引：" + interfaceIndex);
            sb.append("interface" + i + " [label=\"接口" + i + "\",shape=doublecircle];\n");
            sb.append("interface" + i + " -> constant_item_" + interfaceIndex + ";\n");
        }


        int fieldCount = dis.readUnsignedShort();
        System.out.println("字段数量：" + fieldCount);
        for (int i = 0; i < fieldCount; i++) {
            int accessFlags = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();

            sb.append("field" + i + " [label=\"字段" + i + "\\n" + FieldAccessFlagsUtil.getAccessFlagDetail(accessFlags) + "\",style=filled, shape=box];\n");
            sb.append("field" + i + " -> constant_item_" + nameIndex + "[label=\"name\"];\n");
            sb.append("field" + i + " -> constant_item_" + descriptorIndex + "[label=\"descriptor\"];\n");
            System.out.println("字段" + i + "的访问标志：" + FieldAccessFlagsUtil.getAccessFlagDetail(accessFlags) + "，名称索引：#" + nameIndex + "，描述符索引：#" + descriptorIndex);
            int attributesCount = dis.readUnsignedShort();
            System.out.println("字段" + i + "的属性数量：" + attributesCount);
            for (int j = 0; j < attributesCount; j++) {
                int attributeNameIndex = dis.readUnsignedShort();
                int attributeLength = dis.readInt();
                AttributeVerbose verbose = AttributeVerboseFactory.createAttributeVerbose(
                        "field_" + i + "_attribute_" + j,
                        allConstant, attributeNameIndex, attributeLength, dis);
                verbose.print("field" + i, sb);
            }
        }

        int methodCount = dis.readUnsignedShort();
        System.out.println("方法数量：" + methodCount);
        for (int i = 0; i < methodCount; i++) {
            int accessFlags = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();
            sb.append("method" + i + " [label=\"方法" + i + "\\n" + MethodAccessFlagsUtil.getAccessFlagDetail(accessFlags) + "\",style=filled, shape=box];\n");
            sb.append("method" + i + " -> constant_item_" + nameIndex + ";\n");
            sb.append("method" + i + " -> constant_item_" + descriptorIndex + ";\n");
            System.out.println("方法" + i + "的访问标志：" + MethodAccessFlagsUtil.getAccessFlagDetail(accessFlags) + "，名称索引：#" + nameIndex + "，描述符索引：#" + descriptorIndex);
            int attributesCount = dis.readUnsignedShort();
            System.out.println("方法" + i + "的属性数量：" + attributesCount);
            for (int j = 0; j < attributesCount; j++) {
                int attributeNameIndex = dis.readUnsignedShort();
                int attributeLength = dis.readInt();
                System.out.println("方法" + i + "的属性" + j + "的名称：" + attributeNameIndex);
                System.out.println("方法" + i + "的属性" + j + "的长度：" + attributeLength);
                // 解析属性
                if (attributeNameIndex == 1) {
                    int attributeNameIndex2 = dis.readUnsignedShort();
                    int attributeLength2 = dis.readInt();
                    System.out.println("方法" + i + "的属性" + j + "的属性" + attributeNameIndex2 + "的长度：" + attributeLength2);
                    dis.skipBytes(attributeLength2);
                } else if (attributeNameIndex == 12) {
                    int maxStack = dis.readUnsignedShort();
                    int maxLocals = dis.readUnsignedShort();
                    int codeLength = dis.readInt();
                    System.out.println("方法" + i + "的属性" + j + "的最大操作数栈：" + maxStack);
                    System.out.println("方法" + i + "的属性" + j + "的局部变量表大小：" + maxLocals);
                    System.out.println("方法" + i + "的属性" + j + "的字节码长度：" + codeLength);
                    dis.skipBytes(codeLength);
                    int exceptionTableLength = dis.readUnsignedShort();
                    System.out.println("方法" + i + "的属性" + j + "的异常表长度：" + exceptionTableLength);
                    dis.skipBytes(exceptionTableLength * 8);
                    int attributesCount2 = dis.readUnsignedShort();
                    System.out.println("方法" + i + "的属性" + j + "的属性数量：" + attributesCount2);
                    for (int k = 0; k < attributesCount2; k++) {
                        int attributeNameIndex2 = dis.readUnsignedShort();
                        int attributeLength2 = dis.readInt();
                        System.out.println("方法" + i + "的属性" + j + "的属性" + k + "的名称：" + attributeNameIndex2);
                        System.out.println("方法" + i + "的属性" + j + "的属性" + k + "的长度：" + attributeLength2);
                        dis.skipBytes(attributeLength2);
                    }
                } else {
                    dis.skipBytes(attributeLength);
                }

                dis.skipBytes(attributeLength);
            }
        }

        sb.append("}");

        // 获取当前 Class 文件名称
        File classFile = new File(filePath);
        String classFileName = classFile.getName().replace(".class", "");
        writeToFile(projectPath + "/class_info/output/" + classFileName + ".dot", sb.toString());
        dis.close();
        fis.close();

        // 执行命令
        String cmd = "dot -Tpng " + projectPath + "/class_info/output/" + classFileName + ".dot -o " +
                projectPath + "/class_info/output/" + classFileName + ".png";
        Runtime.getRuntime().exec(cmd);
    }


    private static void writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.flush();
        fw.close();
    }
}
