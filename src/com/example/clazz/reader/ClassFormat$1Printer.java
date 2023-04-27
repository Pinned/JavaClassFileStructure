package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.File;
import java.io.IOException;

public class ClassFormat$1Printer implements IClassToGraphPrinter {
    @Override
    public File[] classFilePath(File aimClassInfoPath) {
        return new File[]{
                new File(aimClassInfoPath, "ClassFormat$1.class")
        };
    }

    @Override
    public void print(String filePath, ClassDot classDot) {
        try {
//            ClassToGraphTools.printGraph(filePath, classDot);
            ClassToGraphTools.printConstantGraph(filePath, classDot);
            classDot.resetPrintStatus();
            ClassToGraphTools.generateGraph(filePath, "ClassFormat$1", toCustomGraph(classDot));
        } catch (IOException e) {
        }
    }


    public String toCustomGraph(ClassDot classDot) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        int fieldIndex = 0;
        for (DotItem childDot : classDot.rootItem.childDots) {
            if (childDot.isNodeNameStart("relation_this")) {
                sb.append(childDot.toDotGraph());
            }
            if (childDot.isNodeNameStart("field")) {
                if (fieldIndex <= 0) {
                    sb.append(childDot.toDotGraph());
                }
                fieldIndex++;
            }
        }

        // 写死第一个常量
        sb.append(classDot.allConstantItem.get("1").toDotGraph());
        sb.append("}");
        return sb.toString();
    }

}
