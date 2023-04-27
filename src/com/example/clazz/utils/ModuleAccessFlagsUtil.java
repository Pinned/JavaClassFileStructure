package com.example.clazz.utils;

public class ModuleAccessFlagsUtil {
    public static String getAccessFlagDetail(int accessFlags) {
        StringBuilder sb = new StringBuilder();
        if ((accessFlags & 0x0020) != 0) {
            sb.append("ACC_OPEN");
        }
        if ((accessFlags & 0x1000) != 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("ACC_SYNTHETIC");
        }
        if ((accessFlags & 0x8000) != 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("ACC_MANDATED");
        }
        return sb.toString();
    }
}
