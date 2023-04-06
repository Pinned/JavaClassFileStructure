package com.example.clazz.dot;

public class ArrayDotItem extends DotItem {
    private int constantIndex = 0;

    public ArrayDotItem(String prefix, int index, String showValue) {
        super(prefix + "_" + index, showValue);
        this.constantIndex = index;
    }


    @Override
    public String getShowValue() {
        String superShowValue = super.getShowValue();
        return "#" + constantIndex + "\\n" + superShowValue;
    }
}
