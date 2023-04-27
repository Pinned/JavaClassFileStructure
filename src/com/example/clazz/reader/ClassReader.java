package com.example.clazz.reader;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.AttributeVerboseFactory;
import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.constant.ConstantVerboseFactory;
import com.example.clazz.dot.*;
import com.example.clazz.utils.ClassAccessFlagsUtil;
import com.example.clazz.utils.FieldAccessFlagsUtil;
import com.example.clazz.utils.MethodAccessFlagsUtil;

import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;

public class ClassReader {

    public static ClassDot parseClassFile(String filePath, String className) throws IOException {
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

        dis.close();
        fis.close();

        // 获取当前 Class 文件名称
        return classDot;
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
            DotItem fieldAccessFlag = new DotItem("access_flg", MethodAccessFlagsUtil.getAccessFlagDetail(accessFlags))
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
                    .shape(DotShape.BOX);
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
        DotItem attributeCountDot = null;
        attributeCountDot = new DotItem("attribute_count", "属性\\n" + attributesCount + "个")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.BOX);
        parent.addChild("attribute", attributeCountDot);

        for (int j = 0; j < attributesCount; j++) {
            AttributeVerbose verbose = AttributeVerboseFactory.createAttributeVerbose(classDot, dis);
            DotItem item = verbose.createDotItem(classDot, attributeCountDot, j);
            attributeCountDot.addChild("", item);
        }
    }

    private static void readInterfaces(DataInputStream dis, ClassDot classDot) throws IOException {
        int interfaceCount = dis.readUnsignedShort();
        System.out.println("接口数量：" + interfaceCount);
        DotItem interfaceDot = new DotItem("relation_interface", "interface\\n" + interfaceCount + "个")
                .color(Color.BLUE)
                .shape(DotShape.BOX);
        for (int i = 0; i < interfaceCount; i++) {
            ArrayDotItem arrayDotItem = new ArrayDotItem("interface", i, "")
                    .style(DotStyle.DASHED)
                    .parent(interfaceDot);
            int classIndex = dis.readUnsignedShort();
            arrayDotItem.addChild("", classDot.getConstantItem(classIndex));
            interfaceDot.addChild("", arrayDotItem);
        }
        classDot.addChild("", interfaceDot);
    }

    private static void readClassClassIndex(DataInputStream dis, ClassDot classDot, String name) throws IOException {
        DotItem dot = new DotItem("relation_" + name, name)
                .color(Color.BLUE)
                .style(DotStyle.DASHED)
                .shape(DotShape.BOX);
        int classIndex = dis.readUnsignedShort();
        dot.addChild("", classDot.getConstantItem(classIndex));
        classDot.addChild("", dot);
    }

    private static void readClassAccessFlag(DataInputStream dis, ClassDot classDot) throws IOException {
        // 读取 access flag \ this class \ super class
        int classAccessFlags = dis.readUnsignedShort();
        System.out.println("类访问标志：" + ClassAccessFlagsUtil.getAccessFlagDetail(classAccessFlags));
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
            constantVerbose.printInConsole(i);
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
        ConstantArrayDotItem dotItem = new ConstantArrayDotItem(0, "无");
        classDot.addConstantItem(0, dotItem);
        classDot.addChild("constant", dotItem);

    }

}
