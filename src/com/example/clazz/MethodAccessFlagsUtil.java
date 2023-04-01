package com.example.clazz;

public class MethodAccessFlagsUtil {
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
        if ((accessFlags & 0x0020) != 0) {
            sb.append("ACC_SYNCHRONIZED ");
        }
        if ((accessFlags & 0x0040) != 0) {
            sb.append("ACC_BRIDGE ");
        }
        if ((accessFlags & 0x0080) != 0) {
            sb.append("ACC_VARARGS ");
        }
        if ((accessFlags & 0x0100) != 0) {
            sb.append("ACC_NATIVE ");
        }
        if ((accessFlags & 0x0400) != 0) {
            sb.append("ACC_ABSTRACT ");
        }
        if ((accessFlags & 0x0800) != 0) {
            sb.append("ACC_STRICT ");
        }
        if ((accessFlags & 0x1000) != 0) {
            sb.append("ACC_SYNTHETIC ");
        }
        if ((accessFlags & 0x4000) != 0) {
            sb.append("ACC_MANDATED ");
        }
        return sb.toString();
    }
}
