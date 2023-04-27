package com.example.clazz.utils;

public class FieldAccessFlagsUtil {
    public static String getAccessFlagDetail(int accessFlags) {
        StringBuilder sb = new StringBuilder();
        if ((accessFlags & 0x0001) != 0) {
            sb.append("ACC_PUBLIC ");
        }
        if ((accessFlags & 0x0002) != 0) {
            sb.append("ACC_PRIVATE ");
        }
        if ((accessFlags & 0x0004) != 0) {
            sb.append("ACC_PROTECTED ");
        }
        if ((accessFlags & 0x0008) != 0) {
            sb.append("ACC_STATIC ");
        }
        if ((accessFlags & 0x0010) != 0) {
            sb.append("ACC_FINAL ");
        }
        if ((accessFlags & 0x0040) != 0) {
            sb.append("ACC_VOLATILE ");
        }
        if ((accessFlags & 0x0080) != 0) {
            sb.append("ACC_TRANSIENT ");
        }
        if ((accessFlags & 0x1000) != 0) {
            sb.append("ACC_SYNTHETIC ");
        }
        if ((accessFlags & 0x4000) != 0) {
            sb.append("ACC_ENUM ");
        }
        return sb.toString();
    }
}
