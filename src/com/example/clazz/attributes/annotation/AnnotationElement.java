package com.example.clazz.attributes.annotation;

import com.example.clazz.constant.ConstantVerbose;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

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

    public void read(DataInputStream dis, Map<String, ConstantVerbose> constants) throws IOException {
        typeIndex = dis.readUnsignedShort();
        numElementValuePairs = dis.readUnsignedShort();
        elementValuePairs = new AnnotationElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new AnnotationElementValuePair();
            elementValuePairs[i].read(dis, constants);
        }
    }

    public void print(String parent, int index, StringBuffer sb) {
        String currentNodeName = parent + "_" + index;
        sb.append(currentNodeName + "[label=\"annotation" + index + "\"]");
        sb.append(currentNodeName + " -> constant_item_" + typeIndex + "[label=\"type\"]");
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i].print(currentNodeName, i, sb);
            sb.append(currentNodeName + " -> " + currentNodeName + "_" + i + "[label=\"element" + i + "\"]");
        }
    }
}
