package com.example.clazz.format;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(VerboseAttributes.class);
        System.out.println(ClassFormat.class);
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "out");
        File formatDir = new File(file, "production/JavaClassFileStructure/com/example/clazz/format");
        printFile(0, formatDir);

        File classInfoDir = new File(projectPath, "class_info");
        for (File listFile : classInfoDir.listFiles()) {
            if (listFile.isDirectory()) {
                listFile.deleteOnExit();
            }
        }

        String command = "cp -r " + formatDir.getAbsolutePath() + "/ " + classInfoDir.getAbsolutePath();
        Runtime.getRuntime().exec(command);

    }

    private static void printFile(int indicate, File file) {
        for (int i = 0; i < indicate; i++) {
            System.out.print("--");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] listFile = file.listFiles();
            for (File item : listFile) {
                printFile(indicate + 1, item);
            }
        }
    }
}
