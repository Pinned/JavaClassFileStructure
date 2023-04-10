package com.example.clazz;

public class ModuleRequiresAccessFlagsUtil {
    public static String getAccessFlagDetail(int accessFlags) {
        StringBuilder sb = new StringBuilder();
        if ((accessFlags & 0x0020) != 0) {
            sb.append("ACC_TRANSITIVE");
        }
        if ((accessFlags & 0x0040) != 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("ACC_STATIC_PHASE");
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
