package com.example.clazz.attributes;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public abstract class AttributeVerbose {
    public String parentTag;
    public Map<String, ConstantVerbose> constants;
    public int attributeNameIndex;
    public String attributeName;

    public AttributeVerbose(String parentTag, Map<String, ConstantVerbose> constants, int attributeNameIndex) {
        this.constants = constants;
        this.attributeNameIndex = attributeNameIndex;
        this.attributeName = constants.get(String.valueOf(attributeNameIndex)).getValue();
        this.parentTag = parentTag;
    }

    public abstract void readAttribute(int attributeLength, DataInputStream dataInputStream) throws IOException;

    public void print(String parent, StringBuffer sb) {
        sb.append(getCurrentNodeName() + "[label=\"" + parentTag + "\\n" + attributeName + "\", color=\"#FF0\"]");
        sb.append(";\n");
        sb.append(parent + " -> " + getCurrentNodeName() + "[label=\"attribute\"]");
        sb.append(";\n");
        sb.append(getCurrentNodeName() + " -> constant_item_" + attributeNameIndex + "[label=\"attributeName\"]");
        sb.append(";\n");
    }

    public String getCurrentNodeName() {
        return parentTag + "_attribute_item_" + this.getClass().getSimpleName().replace("AttributeVerbose", "");
    }

}
