package com.example.clazz.dot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDot {
    public DotItem rootItem;
    public Map<String, ConstantArrayDotItem> allConstantItem = new HashMap<>();

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


    public String toDotGraph() {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        for (DotItem childDot : rootItem.childDots) {
            sb.append(childDot.toDotGraph());
        }
//        sb.append(rootItem.toDotGraph());
        sb.append("}");
        return sb.toString();
    }

    public String toDotGraph(String prefix) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        for (DotItem childDot : rootItem.childDots) {
            if (childDot.isNodeNameStart(prefix)) {
                sb.append(childDot.toDotGraph());
            }
        }
        sb.append("}");
        return sb.toString();
    }


    public String toDotGraph(String prefix, int index) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        for (DotItem childDot : rootItem.childDots) {
            if (childDot.isNodeNameStart(prefix)) {
                int curr = 0;
                for (DotItem dot : childDot.childDots) {
                    if (curr == index) {
                        sb.append(dot.toDotGraph());
                    }
                    curr++;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private boolean contains(int value, int... index) {
        for (int i = 0; i < index.length; i++) {
            if (index[i] == value) {
                return true;
            }
        }
        return false;
    }

    public String toDotFieldGraph(String prefix, boolean ignoreAttribute, int... index) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        int curr = 0;
        for (DotItem childDot : rootItem.childDots) {
            if (childDot.isNodeNameStart(prefix)) {
                System.out.println(curr);
                if (contains(curr, index)) {
                    System.out.println("contain");
                    sb.append(childDot.toDotGraph(!ignoreAttribute));
                } else {
                    System.out.println("not contain");
                }
                curr++;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public String toDotFieldGraph(String prefix, int... index) {
        return toDotFieldGraph(prefix, false, index);
    }

    public String toConstantGraph() {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        for (DotItem childDot : allConstantItem.values()) {
            sb.append(childDot.toDotGraph());
        }
        sb.append("}");
        return sb.toString();
    }

    public String toConstantGraph(int constantIndex) {
        StringBuffer sb = new StringBuffer();
        sb.append("digraph class_file { \n");
        DotItem childDot = allConstantItem.get(String.valueOf(constantIndex));
        if (childDot != null) {
            sb.append(childDot.toDotGraph());
        }
        sb.append("}");
        return sb.toString();
    }

    public int getSubChildCount(String prefix) {
        for (DotItem childDot : rootItem.childDots) {
            if (childDot.isNodeNameStart(prefix)) {
                return childDot.childDots.size();
            }
        }
        return 0;
    }

    public void resetPrintStatus() {
        resetPrintStatus(rootItem);
    }

    private void resetPrintStatus(DotItem item) {
        item.isPrint = false;
        for (DotItem childDot : item.childDots) {
            resetPrintStatus(childDot);
        }
    }

}
