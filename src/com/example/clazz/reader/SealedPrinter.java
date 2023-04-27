package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;
import java.io.IOException;

public class SealedPrinter implements IClassToGraphPrinter{
    @Override
    public File[] classFilePath(File aimClassInfoPath) {
        return new File[] {
                new File(aimClassInfoPath, "SealedClassInterface.class"),
        };
    }

    @Override
    public void print(String filePath, ClassDot classDot) {
        try {
            ClassToGraphTools.printSingleAttributeGraph(filePath, classDot, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
