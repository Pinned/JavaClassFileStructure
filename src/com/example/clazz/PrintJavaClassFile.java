package com.example.clazz;

import com.example.clazz.constant.*;

import java.io.*;

public class PrintJavaClassFile {
    public static void main(String[] args) throws IOException {
        // 获取当前项目的根目录
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前项目的根目录：" + projectPath);
        String filePath = projectPath + "/class_info/ClassFormat.class";
        FileInputStream fis = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fis);
        int magic = dis.readInt();
        int minorVersion = dis.readUnsignedShort();
        int majorVersion = dis.readUnsignedShort();
        int constantPoolCount = dis.readUnsignedShort();
        System.out.println("魔数：" + Integer.toHexString(magic));
        System.out.println("次版本号：" + minorVersion);
        System.out.println("主版本号：" + majorVersion);
        System.out.println("常量池数量：" + constantPoolCount);
        StringBuffer sb = new StringBuffer("digraph constant_pool { \n");
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = dis.readUnsignedByte();
            ConstantVerbose constantVerbose = ConstantVerboseFactory.createConstant(tag, dis);
            constantVerbose.print(i, sb);
            i += constantVerbose.getSkipCount();
        }
        sb.append("}");
        writeToFile(projectPath + "/class_info/constant_pool.dot", sb.toString());
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.flush();
        fw.close();
    }
}

