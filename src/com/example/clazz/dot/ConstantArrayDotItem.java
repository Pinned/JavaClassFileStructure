package com.example.clazz.dot;

import com.example.clazz.constant.ConstantVerbose;

public class ConstantArrayDotItem extends ArrayDotItem {
    public ConstantVerbose constant;

    public ConstantArrayDotItem(int index, String showLabel) {
        super("constant_item", index, showLabel);
        if (showLabel.length() > 20) {
            this.showValue = this.showValue.replaceAll(";", ";\\\\n");
            this.shape(DotShape.BOX);
        }
    }

    @Override
    public String getNodeName() {
        // Constant 可能会有很多字段指过来，共用同一个字段，名字使用同一个
        return this.name;
    }


}
