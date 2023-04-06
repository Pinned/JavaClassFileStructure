package com.example.clazz.dot;

import java.util.HashSet;
import java.util.Set;

public class DotPool {

    public Set<DotItem> dotItems = new HashSet<>();

    public void addDot(DotItem dotItem) {
        dotItems.add(dotItem);
    }

    public String toDotGraph() {
        StringBuffer sb = new StringBuffer();
        for (DotItem dotItem : dotItems) {
            sb.append(dotItem.toString());
        }
        return sb.toString();
    }
}
