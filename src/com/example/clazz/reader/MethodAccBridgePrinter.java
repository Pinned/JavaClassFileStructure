package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;
import java.io.IOException;

public class MethodAccBridgePrinter implements IClassToGraphPrinter {
    @Override
    public File[] classFilePath(File aimClassInfoPath) {
        return new File[]{
                new File(aimClassInfoPath, "ClassFormat.class")
        };
    }

    @Override
    public void print(String filePath, ClassDot classDot) {
        try {
            classDot.resetPrintStatus();
            ClassToGraphTools.generateGraph(filePath, "MethodC", classDot.toDotFieldGraph("method", true, 1, 2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
