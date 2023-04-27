package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;

public interface IClassToGraphPrinter {

    public File[] classFilePath(File aimClassInfoPath);

    public void print(String filePath, ClassDot classDot);
}
