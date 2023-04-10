package com.example.clazz;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JModFileReader {
    public static void main(String[] args) {
        // 获取当前项目的根目录
        String projectPath = System.getProperty("user.dir");
        File jModFile = new File(projectPath, "java.base.jmod");
        File classInfoDir = new File(projectPath, "class_info");
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(jModFile));
            dataInputStream.read(new byte[4]);
            ZipInputStream zis = new ZipInputStream(dataInputStream, StandardCharsets.UTF_8);
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                System.out.println(zipEntry.getName());
                if (zipEntry.getName().equals("classes/module-info.class")) {
                    DataOutputStream oos = new DataOutputStream(new FileOutputStream(new File(classInfoDir, "java-base-module-info.class")));
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = zis.read(buffer)) > 0) {
                        oos.write(buffer, 0, bytesRead);
                    }
                    oos.close();
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
