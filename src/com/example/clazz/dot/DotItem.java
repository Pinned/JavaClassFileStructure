package com.example.clazz.dot;


import java.awt.*;
import java.util.*;

public class DotItem {
    public DotShape shape = DotShape.CIRCLE;
    public DotStyle style = DotStyle.DEFAULT;
    public Color color = Color.BLACK;
    public DotItem parentDot = null;
    public String name;
    public String showValue;


    public DotItem(String name, String showValue) {
        this.name = name;
        this.showValue = showValue;
    }

    public <T extends DotItem> T  shape(DotShape shape) {
        this.shape = shape;
        return (T) this;
    }

    public <T extends DotItem> T  style(DotStyle style) {
        this.style = style;
        return (T) this;
    }

    public <T extends DotItem> T  color(Color color) {
        this.color = color;
        return (T) this;
    }

    public <T extends DotItem> T parent(DotItem parent) {
        this.parentDot = parent;
        return (T) this;
    }


    public Set<DotItem> childDots = new HashSet<>();
    public Map<DotItem, String> childLabels = new HashMap<>();


    public void addChild(String key, DotItem childDot) {
        childDots.add(childDot);
        childLabels.put(childDot, key);
    }

    public String getNodeName() {
        String dotName = this.name;
        if (parentDot != null) {
            dotName = parentDot.getNodeName() + "_" + dotName;
        }
        return dotName;
    }

    public String getShowValue() {
        return showValue;
    }

    public String generateCurrentNodeDescription() {
        StringBuffer sb = new StringBuffer();
        sb.append("label=\"" + this.getShowValue() + "\"");
        sb.append(",");
        sb.append("shape=" + this.shape.getShowName());
        if (this.style != DotStyle.FILLED) {
            sb.append(",");
            sb.append("style=" + this.style.name().toLowerCase());
        }

        sb.append(",");
        sb.append("color=\"" + getHexColor(this.color) + "\"");
        return sb.toString();
    }

    public String getHexColor(Color color) {
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return hex.toUpperCase();
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getNodeName() + "[" + this.generateCurrentNodeDescription() + "];");
        sb.append("\n");
        for (DotItem childDot : childDots) {
            String childShow = childDot.toString();
            sb.append(childShow);
            sb.append("\n");
            // 添加一条边
            String label = this.childLabels.containsKey(childDot) ? this.childLabels.get(childDot) : "";
            sb.append(this.getNodeName() + "->" + childDot.getNodeName() + "[label=\"" + label + "\"]");
            sb.append("\n");
        }

        return sb.toString();
    }
}
