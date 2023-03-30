package com.example.clazz;

public class ClassAccessFlagsUtil {
    public static String getAccessFlagDetail(int accessFlags) {
        StringBuilder sb = new StringBuilder();
        if ((accessFlags & 0x0001) != 0) {
            sb.append("ACC_PUBLIC ");
        }
        if ((accessFlags & 0x0010) != 0) {
            sb.append("ACC_FINAL ");
        }
        if ((accessFlags & 0x0020) != 0) {
            sb.append("ACC_SUPER ");
        }
        if ((accessFlags & 0x0200) != 0) {
            sb.append("ACC_INTERFACE ");
        }
        if ((accessFlags & 0x0400) != 0) {
            sb.append("ACC_ABSTRACT ");
        }
        if ((accessFlags & 0x1000) != 0) {
            sb.append("ACC_SYNTHETIC ");
        }
        if ((accessFlags & 0x2000) != 0) {
            sb.append("ACC_ANNOTATION ");
        }
        if ((accessFlags & 0x4000) != 0) {
            sb.append("ACC_ENUM ");
        }
        return sb.toString();
    }
}