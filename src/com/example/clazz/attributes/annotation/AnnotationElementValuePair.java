package com.example.clazz.attributes.annotation;

import com.example.clazz.constant.ConstantVerbose;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

public class AnnotationElementValuePair {
    //     u2 num_element_value_pairs;
    //     {   u2            element_name_index;
    //         element_value value;
    //     }
    public int elementNameIndex;
    public AnnotationElementValue value;

    public void read(DataInputStream dis) throws IOException {
        elementNameIndex = dis.readUnsignedShort();
        value = new AnnotationElementValue();
        value.read(dis);
    }

    public void print(String parent, int i, StringBuffer sb) {
        String currentNodeName = parent + "_" + i;
        sb.append(currentNodeName + "[label=\"element" + i + "\"]");
        sb.append(";\n");
        sb.append(currentNodeName + " -> constant_item_" + elementNameIndex + "[label=\"elementName\"]");
        sb.append(";\n");
        value.print(currentNodeName, sb);
        sb.append(currentNodeName + " -> " + currentNodeName + "_value[label=\"value\"]");
        sb.append(";\n");
    }

    public DotItem createDotItem(ClassDot classDot, ArrayDotItem parent, int index) {
        ArrayDotItem elemValuePairDot = new ArrayDotItem("element_value_pair", index, "")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.CIRCLE);

        elemValuePairDot.addChild("elementName", classDot.getConstantItem(elementNameIndex));

        DotItem item = value.createDotItem(classDot, elemValuePairDot, 0);
        elemValuePairDot.addChild("value", item);
        return elemValuePairDot;
    }
}
