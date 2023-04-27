package com.example.clazz.attributes;

import com.example.clazz.utils.MethodAccessFlagsUtil;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodParameter {
    public int nameIndex;
    public int accessFlags;

    public MethodParameter(ClassDot classDot, DataInputStream dis) throws IOException {
        nameIndex = dis.readUnsignedShort();
        accessFlags = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, DotItem superDotItem, int i) {
        ArrayDotItem dotItem = new ArrayDotItem("method_parameter", i, "")
                .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        dotItem.addChild("name", classDot.getConstantItem(nameIndex));
        dotItem.addChild("access_flags", new DotItem("access_flags",
                MethodAccessFlagsUtil.getAccessFlagDetail(accessFlags), dotItem)
                .shape(DotShape.BOX));
        return dotItem;
    }
}
