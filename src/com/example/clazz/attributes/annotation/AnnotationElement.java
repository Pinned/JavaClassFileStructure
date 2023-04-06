package com.example.clazz.attributes.annotation;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class AnnotationElement {
    // annotation 的格式如下
    // annotation {
    //     u2 type_index;
    //     u2 num_element_value_pairs;
    //     {   u2            element_name_index;
    //         element_value value;
    //     } element_value_pairs[num_element_value_pairs];
    // }
    public int typeIndex;
    public int numElementValuePairs;
    public AnnotationElementValuePair[] elementValuePairs;

    public void read(DataInputStream dis) throws IOException {
        typeIndex = dis.readUnsignedShort();
        numElementValuePairs = dis.readUnsignedShort();
        elementValuePairs = new AnnotationElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new AnnotationElementValuePair();
            elementValuePairs[i].read(dis);
        }
    }

    public void print(String parent, int index, StringBuffer sb) {
        String currentNodeName = parent + "_" + index;
        sb.append(currentNodeName + "[label=\"annotation" + index + "\", shape=diamond]");
        sb.append(";\n");
        sb.append(currentNodeName + " -> constant_item_" + typeIndex + "[label=\"type\"]");
        sb.append(";\n");
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i].print(currentNodeName, i, sb);
            sb.append(currentNodeName + " -> " + currentNodeName + "_" + i + "[label=\"element" + i + "\"]");
            sb.append(";\n");
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        ArrayDotItem arrayDotItem = new ArrayDotItem("elements", index, "")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        arrayDotItem.addChild("type", classDot.getConstantItem(typeIndex));

        DotItem elemValuePairs = new DotItem("num_element_value_pairs", String.valueOf(numElementValuePairs))
                .parent(arrayDotItem);
        arrayDotItem.addChild("num", elemValuePairs);


        for (int i = 0; i < numElementValuePairs; i++) {
            DotItem item = elementValuePairs[i].createDotItem(classDot, arrayDotItem, i);
            arrayDotItem.addChild("", item);
        }
        return arrayDotItem;
    }
}
