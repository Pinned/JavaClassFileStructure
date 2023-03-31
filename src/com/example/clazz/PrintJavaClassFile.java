package com.example.clazz;

import com.example.clazz.constant.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrintJavaClassFile {
    public static void main(String[] args) throws IOException {
        // 获取当前项目的根目录
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前项目的根目录：" + projectPath);
        String filePath = projectPath + "/class_info/ClassFormat$1.class";
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
        StringBuffer sb = new StringBuffer("digraph constant_pool { \n");
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = dis.readUnsignedByte();
            ConstantVerbose constantVerbose = ConstantVerboseFactory.createConstant(tag, dis);
            constantVerbose.print(i, sb);
            i += constantVerbose.getSkipCount();
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
            sb.append("field" + i + " -> constant_item_" + nameIndex + ";\n");
            sb.append("field" + i + " -> constant_item_" + descriptorIndex + ";\n");
            System.out.println("字段" + i + "的访问标志：" + FieldAccessFlagsUtil.getAccessFlagDetail(accessFlags) + "，名称索引：#" + nameIndex + "，描述符索引：#" + descriptorIndex);
            int attributesCount = dis.readUnsignedShort();
            System.out.println("字段" + i + "的属性数量：" + attributesCount);
            for (int j = 0; j < attributesCount; j++) {
                int attributeNameIndex = dis.readUnsignedShort();
                int attributeLength = dis.readInt();
                System.out.println("字段" + i + "的属性" + j + "的名称：" + attributeNameIndex);
                System.out.println("字段" + i + "的属性" + j + "的长度：" + attributeLength);
                dis.skipBytes(attributeLength);
            }
        }

        sb.append("}");
        writeToFile(projectPath + "/class_info/constant_pool.dot", sb.toString());
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
