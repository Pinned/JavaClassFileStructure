package com.example.clazz.dot;

import com.example.clazz.constant.ConstantVerbose;

public class ConstantArrayDotItem extends ArrayDotItem {
    public ConstantVerbose constant;

    public ConstantArrayDotItem(int index, String showLabel) {
        super("constant_item", index, showLabel);
        if (showLabel.length() > 20) {
            if (this.showValue.contains(";")) {
                if (this.showValue.contains("Utf8\\(")) {
                    this.showValue = this.showValue.replaceAll("Utf8\\(", "Utf8(\\\\l");
                } else {
                    this.showValue = this.showValue.replaceAll("Utf8\\(\\(", "Utf8((\\\\l");
                }
//                this.showValue = this.showValue.replaceAll("Utf8\\(+", "Utf8(\\\\l");
                this.showValue = this.showValue.replaceAll(";", ";\\\\l");
                this.showValue += "\\l";
            }
            this.shape(DotShape.BOX);
        }
    }

    @Override
    public String getNodeName() {
        // Constant 可能会有很多字段指过来，共用同一个字段，名字使用同一个
        return this.name;
    }


}
