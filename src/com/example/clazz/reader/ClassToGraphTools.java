package com.example.clazz.reader;

import com.example.clazz.dot.ClassDot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassToGraphTools {

    public static void printGraph(String filePath, ClassDot classDot) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "All", classDot.toDotGraph());
    }

    public static void printFiledGraph(String filePath, ClassDot classDot) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Field", classDot.toDotGraph("field"));
    }

    public static void printMethodGraph(String filePath, ClassDot classDot) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Method", classDot.toDotGraph("method"));
    }

    public static void printAttributeGraph(String filePath, ClassDot classDot) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Attribute", classDot.toDotGraph("attribute"));
    }

    public static void printSingleAttributeGraph(String filePath, ClassDot classDot, int index) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Attribute_" + index, classDot.toDotGraph("attribute", index));
    }

    public static void printAllSingleAttributeGraph(String filePath, ClassDot classDot) throws IOException {
        int attributeCount = classDot.getSubChildCount("attribute");
        for (int i = 0; i < attributeCount; i++) {
            printSingleAttributeGraph(filePath, classDot, i);
        }
    }

    public static void printSingleFieldGraph(String filePath, ClassDot classDot, int... index) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Field_" + index, classDot.toDotFieldGraph("field", index));
    }

    public static void printAllSingleFieldGraph(String filePath, ClassDot classDot) throws IOException {
        int fieldCount = classDot.getSubChildCount("field");
        for (int i = 0; i < fieldCount; i++) {
            printSingleFieldGraph(filePath, classDot, i);
        }
    }

    public static void printSingleMethodGraph(String filePath, ClassDot classDot, int... index) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Method_" + index, classDot.toDotFieldGraph("method", index));
    }

    public static void printAllSingleMethodGraph(String filePath, ClassDot classDot) throws IOException {
        int methodCount = classDot.getSubChildCount("method");
        for (int i = 0; i < methodCount; i++) {
            printSingleMethodGraph(filePath, classDot, i);
        }
    }

    public static void printConstantGraph(String filePath, ClassDot classDot) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Constant", classDot.toConstantGraph());
    }

    public static void printSingleConstantGraph(String filePath, ClassDot classDot, int index) throws IOException {
        classDot.resetPrintStatus();
        generateGraph(filePath, "Constant_" + index, classDot.toConstantGraph(index));
    }

    public static void printAllSingleConstantGraph(String filePath, ClassDot classDot) throws IOException {
        int constantCount = classDot.getSubChildCount("constant");
        for (int i = 0; i < constantCount; i++) {
            printSingleConstantGraph(filePath, classDot, i);
        }
    }

    public static void printRelationGraph(String filePath, ClassDot classDot) throws IOException {

        classDot.resetPrintStatus();
        generateGraph(filePath, "Relation", classDot.toDotGraph("relation"));
    }

    private static String getClassFileName(String filePath) {
        File classFile = new File(filePath);
        String classFileName = classFile.getName().replace(".class", "");
        return classFileName;
    }

    public static void generateGraph(String filePath, String fileName, String graph) throws IOException {
        String classFileName = getClassFileName(filePath);
        fileName = classFileName + fileName;
        File parentFile = new File(filePath).getParentFile();
        File output = new File(parentFile, "output");
        if (!output.exists()) {
            output.mkdirs();
        }

        writeToFile(new File(output, fileName + ".dot").getAbsolutePath(), graph);
        // 执行命令
        Runtime.getRuntime().exec(new String[]{
                "dot", "-Tpng", new File(output, fileName + ".dot").getAbsolutePath(), "-o", new File(output, fileName + ".png").getAbsolutePath()
        });
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
