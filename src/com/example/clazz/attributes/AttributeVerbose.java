package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public abstract class AttributeVerbose {
    public int attributeNameIndex;
    public String attributeName;
    public int attributeLength;


    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem item = new DotItem("attribute_" + index, "")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        item.addChild("attributeName", classDot.getConstantItem(attributeNameIndex));

        DotItem length = new DotItem("length", attributeLength + " Byte")
                .parent(item);
        item.addChild("length", length);
        return item;
    }

    public AttributeVerbose(int attributeNameIndex, String attributeName, DataInputStream dis) throws IOException {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeName = attributeName;
        this.attributeLength = dis.readInt();
    }


//    public void print(String parent, StringBuffer sb) {
//        sb.append(getCurrentNodeName() + "[label=\"\", shape = circle, style=filled]");
//        sb.append(";\n");
//        sb.append(parent + " -> " + getCurrentNodeName() + "[label=\"attribute\"]");
//        sb.append(";\n");
//        sb.append(getCurrentNodeName() + " -> constant_item_" + attributeNameIndex + "[label=\"attributeName\"]");
//        sb.append(";\n");
//    }
//
//    public String getCurrentNodeName() {
//        return parentTag + "_attribute_item_" + this.getClass().getSimpleName().replace("AttributeVerbose", "");
//    }

}
