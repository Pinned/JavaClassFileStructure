package com.example.clazz.attributes.module;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class Opens {
    public int opensIndex;
    public int opensFlags;
    public int opensToCount;
    public int[] opensToIndex;

    public Opens(ClassDot classDot, DataInputStream dis) throws IOException {
        opensIndex = dis.readUnsignedShort();
        opensFlags = dis.readUnsignedShort();
        opensToCount = dis.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = dis.readUnsignedShort();
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem opensDotItem, int i) {
        ArrayDotItem opensItem = new ArrayDotItem("opens", i, "")
                .parent(opensDotItem);
        opensItem.addChild("index", classDot.getConstantItem(opensIndex));
        opensItem.addChild("flags", new DotItem("flags", ModuleOpensAccessFlagsUtil.getAccessFlagDetail(opensFlags), opensItem));
        opensItem.addChild("toCount", new DotItem("toCount", String.valueOf(opensToCount), opensItem));
        for (int j = 0; j < opensToCount; j++) {
            opensItem.addChild("toIndex", classDot.getConstantItem(opensToIndex[j]));
        }
        return opensItem;
    }

    private static class ModuleOpensAccessFlagsUtil {
        public static String getAccessFlagDetail(int opensFlags) {
            StringBuffer sb = new StringBuffer();
            if ((opensFlags & 0x1000) != 0) {
                sb.append("ACC_SYNTHETIC ");
            }
            if ((opensFlags & 0x8000) != 0) {
                sb.append("ACC_MANDATED ");
            }
            return sb.toString();
        }
    }
}
