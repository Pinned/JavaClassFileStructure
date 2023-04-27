package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;
import java.io.IOException;

public class AllClassPrinter implements IClassToGraphPrinter {


    @Override
    public File[] classFilePath(File aimClassInfoPath) {
        return aimClassInfoPath.listFiles();
    }

    @Override
    public void print(String filePath, ClassDot classDot) {
        try {
            ClassToGraphTools.printGraph(filePath, classDot);
        } catch (IOException e) {
        }
    }
}
