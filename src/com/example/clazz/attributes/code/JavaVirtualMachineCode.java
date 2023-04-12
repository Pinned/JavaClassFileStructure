package com.example.clazz.attributes.code;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class JavaVirtualMachineCode {


    public static String readByteCode(byte[] code) {
        StringBuilder sb = new StringBuilder();
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(code));
        try {
            while (dis.available() > 0) {
                int codeByte = dis.read() & 0xFF;
                Opcode opcode = Opcode.createByCode(codeByte);
                if (opcode == Opcode.WIDE) {
                    codeByte = dis.read() & 0xFF;
                    opcode = Opcode.createByCode(codeByte);
                    sb.append("#" + Integer.toHexString(codeByte) + " " + opcode + " " + readOperationNumber(opcode, dis, true) + "\\l");
                } else {
                    sb.append("#" + Integer.toHexString(codeByte) + " " + Opcode.createByCode((byte) codeByte) + " " + readOperationNumber(opcode, dis, true) + "\\l");
                }
            }
        } catch (Exception e) {
        }

        return sb.toString();
    }

    public static String readOperationNumber(Opcode code, DataInputStream dis, boolean isWide) throws IOException {
        switch (code) {
            case WIDE:
            case NOP:
            case ACONST_NULL:
            case ICONST_M1:
            case ICONST_0:
            case ICONST_1:
            case ICONST_2:
            case ICONST_3:
            case ICONST_4:
            case ICONST_5:
            case LCONST_0:
            case LCONST_1:
            case FCONST_0:
            case FCONST_1:
            case FCONST_2:
            case DCONST_0:
            case DCONST_1:
            case ILOAD_0:
            case ILOAD_1:
            case ILOAD_2:
            case ILOAD_3:
            case LLOAD_0:
            case LLOAD_1:
            case LLOAD_2:
            case LLOAD_3:
            case FLOAD_0:
            case FLOAD_1:
            case FLOAD_2:
            case FLOAD_3:
            case DLOAD_0:
            case DLOAD_1:
            case DLOAD_2:
            case DLOAD_3:
            case ALOAD_0:
            case ALOAD_1:
            case ALOAD_2:
            case ALOAD_3:
            case IALOAD:
            case LALOAD:
            case FALOAD:
            case DALOAD:
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case SALOAD:
            case ISTORE_0:
            case ISTORE_1:
            case ISTORE_2:
            case ISTORE_3:
            case LSTORE_0:
            case LSTORE_1:
            case LSTORE_2:
            case LSTORE_3:
            case FSTORE_0:
            case FSTORE_1:
            case FSTORE_2:
            case FSTORE_3:
            case DSTORE_0:
            case DSTORE_1:
            case DSTORE_2:
            case DSTORE_3:
            case ASTORE_0:
            case ASTORE_1:
            case ASTORE_2:
            case ASTORE_3:
            case IASTORE:
            case LASTORE:
            case FASTORE:
            case DASTORE:
            case AASTORE:
            case BASTORE:
            case CASTORE:
            case SASTORE:
            case POP:
            case POP2:
            case DUP:
            case DUP_X1:
            case DUP_X2:
            case DUP2:
            case DUP2_X1:
            case DUP2_X2:
            case SWAP:
            case IADD:
            case LADD:
            case FADD:
            case DADD:
            case ISUB:
            case LSUB:
            case FSUB:
            case DSUB:
            case IMUL:
            case LMUL:
            case FMUL:
            case DMUL:
            case IDIV:
            case LDIV:
            case FDIV:
            case DDIV:
            case IREM:
            case LREM:
            case FREM:
            case DREM:
            case INEG:
            case LNEG:
            case FNEG:
            case DNEG:
            case ISHL:
            case LSHL:
            case ISHR:
            case LSHR:
            case IUSHR:
            case LUSHR:
            case IAND:
            case LAND:
            case IOR:
            case LOR:
            case IXOR:
            case LXOR:
            case I2L:
            case I2F:
            case I2D:
            case L2I:
            case L2F:
            case L2D:
            case F2I:
            case F2L:
            case F2D:
            case D2I:
            case D2L:
            case D2F:
            case I2B:
            case I2C:
            case I2S:
            case LCMP:
            case FCMPL:
            case FCMPG:
            case DCMPL:
            case DCMPG:
            case IRETURN:
            case LRETURN:
            case FRETURN:
            case DRETURN:
            case ARETURN:
            case RETURN:
            case ARRAYLENGTH:
            case ATHROW:
            case MONITORENTER:
            case MONITOREXIT:
            case BREAKPOINT:
            case IMPDEP1:
            case IMPDEP2:
                return "";
            case BIPUSH:
            case LDC:
            case ILOAD:
            case LLOAD:
            case FLOAD:
            case DLOAD:
            case ALOAD:
            case ISTORE:
            case LSTORE:
            case FSTORE:
            case DSTORE:
            case ASTORE:
            case RET:
            case NEWARRAY:
                if (isWide) {
                    return " " + dis.readUnsignedShort();
                } else {
                    return " " + dis.readUnsignedByte();
                }
            case LDC_W:
            case LDC2_W:
            case GETSTATIC:
            case PUTSTATIC:
            case GETFIELD:
            case PUTFIELD:
            case INVOKEVIRTUAL:
            case INVOKESPECIAL:
            case INVOKESTATIC:
            case NEW:
            case ANEWARRAY:
            case CHECKCAST:
            case INSTANCEOF:
                // the only immediate short instruction that does
                // not have an immediate constant pool reference
            case SIPUSH:
                return " #" + dis.readUnsignedShort();
            case IFEQ:
            case IFNE:
            case IFLT:
            case IFGE:
            case IFGT:
            case IFLE:
            case IF_ICMPEQ:
            case IF_ICMPNE:
            case IF_ICMPLT:
            case IF_ICMPGE:
            case IF_ICMPGT:
            case IF_ICMPLE:
            case IF_ACMPEQ:
            case IF_ACMPNE:
            case GOTO:
            case JSR:
            case IFNULL:
            case IFNONNULL:
                return " " + dis.readShort();

            case GOTO_W:
            case JSR_W:
                return " " + dis.readInt();

            // subject to wide
            case IINC:
                if (isWide) {
                    return " " + dis.readUnsignedShort() + " " + dis.readShort();
                } else {
                    return " " + dis.readUnsignedByte() + " " + dis.readByte();
                }

            case TABLESWITCH:
                // skip padding
                int defaultOffset = dis.readInt();
                int low = dis.readInt();
                int high = dis.readInt();
                int[] offsets = new int[high - low + 1];
                for (int i = 0; i < offsets.length; i++) {
                    offsets[i] = dis.readInt();
                }
                return " " + defaultOffset + " " + low + " " + high + " " + Arrays.toString(offsets);

            case LOOKUPSWITCH:
                // skip padding
                defaultOffset = dis.readInt();
                int npairs = dis.readInt();
                int[] matchOffsets = new int[npairs * 2];
                for (int i = 0; i < matchOffsets.length; i++) {
                    matchOffsets[i] = dis.readInt();
                }
                return " " + defaultOffset + " " + npairs + " " + Arrays.toString(matchOffsets);

            case INVOKEINTERFACE:
                return " " + dis.readUnsignedShort() + " " + dis.readUnsignedByte() + " " + dis.readUnsignedByte();

            case INVOKEDYNAMIC:
                return " " + dis.readUnsignedShort() + " " + dis.readUnsignedShort();

            case MULTIANEWARRAY:
                return " " + dis.readUnsignedShort() + " " + dis.readUnsignedByte();
        }
        return "";
    }

}
