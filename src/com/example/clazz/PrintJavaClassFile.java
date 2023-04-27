package com.example.clazz;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.AttributeVerboseFactory;
import com.example.clazz.constant.*;
import com.example.clazz.dot.*;
import com.example.clazz.format.Main;
import com.example.clazz.reader.AllClassPrinter;
import com.example.clazz.reader.ClassFormat$1Printer;
import com.example.clazz.reader.ClassReader;
import com.example.clazz.reader.IClassToGraphPrinter;
import com.example.clazz.utils.ClassAccessFlagsUtil;
import com.example.clazz.utils.FieldAccessFlagsUtil;
import com.example.clazz.utils.MethodAccessFlagsUtil;

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

        IClassToGraphPrinter[] allPrinter = new IClassToGraphPrinter[]{
//                new AllClassPrinter(),
                new ClassFormat$1Printer(),
        };
        for (IClassToGraphPrinter printer : allPrinter) {
            File[] files = printer.classFilePath(file);
            for (File item : files) {
                if (item.isFile() && item.getName().endsWith(".class")) {
                    ClassDot dot = ClassReader.parseClassFile(item.getAbsolutePath(), item.getName());
                    printer.print(item.getAbsolutePath(), dot);
                }
            }
        }


    }
}
