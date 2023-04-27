package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;
import java.io.IOException;

public class MethodAccStrictPrinter implements IClassToGraphPrinter {
    @Override
    public File[] classFilePath(File aimClassInfoPath) {
        return new File[]{
                new File(aimClassInfoPath, "ClassFormat.class")
        };
    }

    @Override
    public void print(String filePath, ClassDot classDot) {
        classDot.resetPrintStatus();
        try {
            ClassToGraphTools.generateGraph(filePath, "MethodC", classDot.toDotFieldGraph("method", true, 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
