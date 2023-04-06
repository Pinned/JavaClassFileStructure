package com.example.clazz.constant;

import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.ConstantArrayDotItem;
import com.example.clazz.dot.DotItem;

import java.lang.reflect.Field;

public interface ConstantVerbose {

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
        return item;
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
