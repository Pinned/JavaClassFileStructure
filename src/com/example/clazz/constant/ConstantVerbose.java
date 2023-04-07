package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.lang.reflect.Field;

public interface ConstantVerbose {

    default void printInConsole(int index) {
        String verbose = this.getClass().getSimpleName().replace("ConstantVerbose", "");
        System.out.print("#" + index + " = " + verbose);
        try {
            Field value = this.getClass().getDeclaredField("value");
            value.setAccessible(true);
            System.out.print("\t" + value.get(this));
        } catch (Exception e) {
        }
        int i = 0;
        Field[] allFields = this.getClass().getDeclaredFields();
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
            } catch (Exception e) {
                // 没有直接跳过
            }
        }
        System.out.println();
    }

    default ConstantArrayDotItem createDotItem(int index, ClassDot classDot) {

        String verbose = this.getClass().getSimpleName().replace("ConstantVerbose", "");
        String showLabel = "";
        try {
            Field value = this.getClass().getDeclaredField("value");
            value.setAccessible(true);
            showLabel = verbose + "(" + value.get(this) + ")";
        } catch (Exception e) {
            // 没有 value 字段，直接生成 dot 语法
            showLabel = verbose + "()";
        }
        ConstantArrayDotItem item = new ConstantArrayDotItem(index, showLabel);
        item.constant = this;
        return item;
    }

    default void updateConstantIndex(DotItem item, ClassDot classDot) {
        Field[] allFields = this.getClass().getDeclaredFields();
        int i = 0;
        for (Field field : allFields) {
            try {
                field.setAccessible(true);
                // 如果当前为 value 字段，直接跳过
                if (field.getName().equals("value")) {
                    continue;
                }
                String fieldName = field.getName().replaceAll("Index", "");
                item.addChild(fieldName, classDot.getConstantItem((Integer) field.get(this)));
                i++;
            } catch (Exception e) {
                // 没有直接跳过
            }
        }
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
