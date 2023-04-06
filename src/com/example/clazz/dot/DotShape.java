package com.example.clazz.dot;

public enum DotShape {
    ELLIPSE,BOX, CIRCLE, DOUBLECIRCLE, DIAMOND;

    public String getShowName() {
        return this.name().toLowerCase();
    }
}
