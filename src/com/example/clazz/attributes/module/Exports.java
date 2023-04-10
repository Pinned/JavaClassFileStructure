package com.example.clazz.attributes.module;

import com.example.clazz.dot.ArrayDotItem;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class Exports {
    public int exportsIndex;
    public int exportsFlags;
    public int exportsToCount;
    public int[] exportsToIndex;

    public Exports(ClassDot classDot, DataInputStream dis) throws IOException {
        exportsIndex = dis.readUnsignedShort();
        exportsFlags = dis.readUnsignedShort();
        exportsToCount = dis.readUnsignedShort();
        exportsToIndex = new int[exportsToCount];
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex[i] = dis.readUnsignedShort();
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem exportsDotItem, int i) {
        ArrayDotItem exportsItem = new ArrayDotItem("exports", i, "")
                .parent(exportsDotItem);
        exportsItem.addChild("index", classDot.getConstantItem(exportsIndex));
        exportsItem.addChild("flags", new DotItem("flags", ModuleExportsAccessFlagsUtil.getAccessFlagDetail(exportsFlags), exportsItem));
        exportsItem.addChild("toCount", new DotItem("toCount", String.valueOf(exportsToCount), exportsItem));
        for (int j = 0; j < exportsToCount; j++) {
            exportsItem.addChild("toIndex", classDot.getConstantItem(exportsToIndex[j]));
        }
        return exportsItem;
    }

    private static class ModuleExportsAccessFlagsUtil {
        public static String getAccessFlagDetail(int exportsFlags) {
            StringBuffer sb = new StringBuffer();
            if ((exportsFlags & 0x1000) != 0) {
                sb.append("ACC_SYNTHETIC ");
            }
            if ((exportsFlags & 0x8000) != 0) {
                sb.append("ACC_MANDATED ");
            }
            return sb.toString();
        }
    }
}
