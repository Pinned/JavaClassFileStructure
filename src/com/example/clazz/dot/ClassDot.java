package com.example.clazz.dot;

import java.util.HashMap;
import java.util.Map;

public class ClassDot {
    public DotItem rootItem;
    Map<String, ConstantArrayDotItem> allConstantItem = new HashMap<>();

    public ClassDot(String className) {
        this.rootItem = new DotItem("class", className);
    }


    public void addConstantItem(int index, ConstantArrayDotItem item) {
        allConstantItem.put(String.valueOf(index), item);
    }

    public void addChild(String key, DotItem item) {
        item.parentDot = rootItem;
        rootItem.addChild(key, item);
    }

    public ConstantArrayDotItem getConstantItem(int index) {
        ConstantArrayDotItem item = allConstantItem.get(String.valueOf(index));
        if (item == null) {
            throw new RuntimeException("找不到 index 为 " + index + " 的常量池字段");
        }
        return item;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        sb.append(rootItem.toString());
        sb.append("}");
        return sb.toString();
    }
}
