package com.example.clazz;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.AttributeVerboseFactory;
import com.example.clazz.constant.*;
import com.example.clazz.dot.*;
import com.example.clazz.format.Main;

import java.awt.*;
import java.io.*;
import java.util.LinkedHashSet;
import java.util.Map;

public class PrintJavaClassFile {
    public static void main(String[] args) throws IOException {
        Main.main(args);
        // 获取当前项目的根目录
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前项目的根目录：" + projectPath);
        File file = new File(projectPath, "class_info");
        File mainFile = new File(file, "IntRange.class");
        parseClassFile(projectPath, mainFile.getAbsolutePath(), mainFile.getName());
        for (File listFile : file.listFiles()) {
            if (listFile.isFile() && listFile.getName().endsWith(".class")) {
                System.out.println();
                System.out.println(listFile.getName());
                parseClassFile(projectPath, listFile.getAbsolutePath(), listFile.getName());
            }
        }
    }

    private static void parseClassFile(String projectPath, String filePath, String className) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fis);
        ClassDot classDot = new ClassDot(className);
        int magic = dis.readInt();
        int minorVersion = dis.readUnsignedShort();
        int majorVersion = dis.readUnsignedShort();
        System.out.println("魔数：" + Integer.toHexString(magic));
        System.out.println("次版本号：" + minorVersion);
        System.out.println("主版本号：" + majorVersion);

        // 读取常量池
        readConstantPool(dis, classDot);
        readClassAccessFlag(dis, classDot);
        // 读取 this class
        readClassClassIndex(dis, classDot, "this_class");
        readClassClassIndex(dis, classDot, "super_class");
        readInterfaces(dis, classDot);


        readField(dis, classDot);

        readMethod(dis, classDot);


        readAttribute(dis, classDot, classDot.rootItem);

        // 获取当前 Class 文件名称
        File classFile = new File(filePath);
        String classFileName = classFile.getName().replace(".class", "");


        writeToFile(projectPath + "/class_info/output/" + classFileName + ".dot", classDot.toDotGraph());
        dis.close();
        fis.close();

        // 执行命令
        String cmd = "dot -Tpng " + projectPath + "/class_info/output/" + classFileName + ".dot -o " + projectPath + "/class_info/output/" + classFileName + ".png";
        Runtime.getRuntime().exec(cmd);
    }

    private static void readMethod(DataInputStream dis, ClassDot classDot) throws IOException {
        int methodCount = dis.readUnsignedShort();
        System.out.println("方法数量：" + methodCount);
        for (int i = 0; i < methodCount; i++) {
            int accessFlags = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();
            ArrayDotItem methodDotItem = new ArrayDotItem("method", i, "方法" + i)
                    .style(DotStyle.FILLED)
                    .shape(DotShape.BOX);
            DotItem fieldAccessFlag = new DotItem("access_flg", FieldAccessFlagsUtil.getAccessFlagDetail(accessFlags))
                    .parent(methodDotItem);
            methodDotItem.addChild("access", fieldAccessFlag);
            methodDotItem.addChild("name", classDot.getConstantItem(nameIndex));
            methodDotItem.addChild("descriptor", classDot.getConstantItem(descriptorIndex));
            readAttribute(dis, classDot, methodDotItem);
            classDot.addChild("", methodDotItem);
        }
    }

    private static void readField(DataInputStream dis, ClassDot classDot) throws IOException {
        int fieldCount = dis.readUnsignedShort();
        System.out.println("字段数量：" + fieldCount);
        for (int i = 0; i < fieldCount; i++) {
            int accessFlags = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();

            DotItem fieldItem = new DotItem("field_" + i, "字段" + i)
                    .style(DotStyle.FILLED)
                    .shape(DotShape.BOX)
                    ;
            DotItem fieldAccessFlag = new DotItem("access_flg", FieldAccessFlagsUtil.getAccessFlagDetail(accessFlags))
                    .parent(fieldItem);
            fieldItem.addChild("access", fieldAccessFlag);
            fieldItem.addChild("name", classDot.getConstantItem(nameIndex));
            fieldItem.addChild("descriptor", classDot.getConstantItem(descriptorIndex));

            readAttribute(dis, classDot, fieldItem);

            classDot.addChild("", fieldItem);
        }
    }

    private static void readAttribute(DataInputStream dis, ClassDot classDot, DotItem parent) throws IOException {
        int attributesCount = dis.readUnsignedShort();
        DotItem attributeCountDot = new DotItem("attribute_count", String.valueOf(attributesCount))
                .parent(parent).style(DotStyle.DASHED);
        parent.addChild("attribute", attributeCountDot);

        for (int j = 0; j < attributesCount; j++) {
            int attributeNameIndex = dis.readUnsignedShort();
            String attributeName = classDot.getConstantItem(attributeNameIndex).constant.getValue();
            AttributeVerbose verbose = AttributeVerboseFactory.createAttributeVerbose(attributeNameIndex, attributeName, dis);
            DotItem item = verbose.createDotItem(classDot, attributeCountDot, j);
            attributeCountDot.addChild("", item);
        }
    }

    private static void readInterfaces(DataInputStream dis, ClassDot classDot) throws IOException {
        int interfaceCount = dis.readUnsignedShort();
        System.out.println("接口数量：" + interfaceCount);
        for (int i = 0; i < interfaceCount; i++) {
            readClassClassIndex(dis, classDot, "interface_" + i, "接口" + i);
        }
    }

    private static void readClassClassIndex(DataInputStream dis, ClassDot classDot, String name) throws IOException {
        readClassClassIndex(dis, classDot, name, name);
    }

    private static void readClassClassIndex(DataInputStream dis, ClassDot classDot, String name, String showValue) throws IOException {
        int thisClass = dis.readUnsignedShort();
        System.out.println(showValue + "：#" + thisClass);
        DotItem thisClassDot = new DotItem(name, showValue);
        thisClassDot.color = Color.BLUE;
        thisClassDot.shape = DotShape.DOUBLECIRCLE;
        classDot.addChild(name, thisClassDot);
        thisClassDot.addChild("", classDot.getConstantItem(thisClass));
    }

    private static void readClassAccessFlag(DataInputStream dis, ClassDot classDot) throws IOException {
        // 读取 access flag \ this class \ super class
        int classAccessFlags = dis.readUnsignedShort();
        System.out.println("访问标志：" + ClassAccessFlagsUtil.getAccessFlagDetail(classAccessFlags));
        DotItem item = new DotItem("class_access_flags", ClassAccessFlagsUtil.getAccessFlagDetail(classAccessFlags))
                .shape(DotShape.BOX);
        classDot.addChild("", item);
    }

    private static void readConstantPool(DataInputStream dis, ClassDot classDot) throws IOException {
        int constantPoolCount = dis.readUnsignedShort();
        System.out.println("常量池数量：" + constantPoolCount);
        LinkedHashSet<ConstantVerbose> allVerbose = new LinkedHashSet<>();
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = dis.readUnsignedByte();
            ConstantVerbose constantVerbose = ConstantVerboseFactory.createConstant(tag, dis);
            allVerbose.add(constantVerbose);
            i += constantVerbose.getSkipCount();
        }

        int index = 1;
        for (ConstantVerbose constantVerbose : allVerbose) {
            ConstantArrayDotItem item = constantVerbose.createDotItem(index, classDot);
            classDot.addConstantItem(index, item);
            classDot.addChild("constant", item);
            index++;
            index += constantVerbose.getSkipCount();
        }

        for (Map.Entry<String, ConstantArrayDotItem> entry : classDot.allConstantItem.entrySet()) {
            ConstantArrayDotItem item = entry.getValue();
            item.constant.updateConstantIndex(item, classDot);
        }

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
