package com.example.clazz.attributes;

import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class RecordComponentInfo {
    public int nameIndex;
    public int descriptorIndex;
    public int attributesCount;
    public AttributeVerbose[] attributes;

    public RecordComponentInfo(ClassDot classDot, DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
        descriptorIndex = dis.readUnsignedShort();
        attributesCount = dis.readUnsignedShort();
        attributes = new AttributeVerbose[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeVerboseFactory.createAttributeVerbose(classDot, dis);
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem superDotItem, int index) {
        ArrayDotItem dotItem = new ArrayDotItem("record_component_info", index, "")
                .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        dotItem.addChild("name", classDot.getConstantItem(nameIndex));
        dotItem.addChild("descriptor", classDot.getConstantItem(descriptorIndex));
        dotItem.addChild("attributes_count", new DotItem("attributes_count", attributesCount + "", dotItem));

        if (attributesCount == 0) {
            return dotItem;
        }
        DotItem attributesDotItem = new DotItem("attributes", "", dotItem)
                .shape(DotShape.CIRCLE).style(DotStyle.DASHED);

        for (int i = 0; i < attributesCount; i++) {
            DotItem item = attributes[i].createDotItem(classDot, attributesDotItem, i);
            attributesDotItem.addChild("", item);
        }
        dotItem.addChild("attributes", attributesDotItem);
        return dotItem;
    }
}
