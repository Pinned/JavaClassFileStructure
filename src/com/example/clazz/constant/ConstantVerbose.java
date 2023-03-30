package com.example.clazz.constant;

import java.lang.reflect.Field;

public interface ConstantVerbose {
    default void print(int index, StringBuffer sb) {
        String verbose = this.getClass().getSimpleName().replace("ConstantVerbose", "");
        System.out.print("#" + index + " = " + verbose);
        try {
            Field value = this.getClass().getDeclaredField("value");
            value.setAccessible(true);
            System.out.print("\t" + value.get(this));
            // 生成 dot 语法
            sb.append("constant_item_" + index + "[label=\"" + index + "\\n " + verbose + "(" + value.get(this) + ")\"]");
            sb.append(";\n");
        } catch (Exception e) {
            // 没有 value 字段，直接生成 dot 语法
            sb.append("constant_item_" + index + "[label=\"" + index + "\\n " + verbose + "()\"]");
            sb.append(";\n");
        }
        Field[] allFields = this.getClass().getDeclaredFields();
        int i = 0;
        for (Field field : allFields) {
            try {
                field.setAccessible(true);
                // 如果当前为 value 字段，直接跳过
                if (field.getName().equals("value")) {
                    continue;
                }
                // 如果是第一个字段，添加一个 \t
                if (i == 0) {
                    System.out.print("\t#" + field.get(this));
                } else {
                    System.out.print(".#" + field.get(this));
                }
                String fieldName = field.getName().replaceAll("Index", "");
                sb.append("constant_item_" + index + " -> constant_item_" + field.get(this) + "[label=\"" + fieldName + "\"]");
                sb.append(";\n");
                i++;
            } catch (Exception e) {
                // 没有直接跳过
            }
        }

        System.out.println();

    }

    default String getValue() {
        try {
            Field value = this.getClass().getDeclaredField("value");
            value.setAccessible(true);
            return value.get(this).toString();
        } catch (Exception e) {
            return "";
        }
    }
    int getSkipCount();
}
