package com.example.clazz.attributes.code;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineCode {
    public int pc;
    public int byteCode;
    public Opcode opcode;
    public List<Integer> allOperationNumber = new ArrayList<>();
    public int skipCount = 0;

    public static MachineCode readMachineCode(int pc, DataInputStream dis) throws IOException {
        MachineCode machineCode = new MachineCode();
        machineCode.pc = pc;
        machineCode.byteCode = dis.read() & 0xFF;
        machineCode.opcode = Opcode.createByCode(machineCode.byteCode);
        boolean isWide = false;
        if (machineCode.opcode == Opcode.WIDE) {
            machineCode.byteCode = dis.read() & 0xFF;
            machineCode.opcode = Opcode.createByCode(machineCode.byteCode);
            isWide = true;
        }
        machineCode.readOperationNumber(machineCode.opcode, dis, isWide);
        machineCode.skipCount = machineCode.allOperationNumber.size();
        return machineCode;
    }


    public String getShowInfo() {
        if (allOperationNumber.size() > 0) {
            return pc + " #" + Integer.toHexString(byteCode) + " " + opcode + " " + Arrays.toString(allOperationNumber.toArray()) + "\\l";
        } else {
            return pc + " #" + Integer.toHexString(byteCode) + " " + opcode + "\\l";
        }
    }

    public int getSkipCount() {
        return skipCount;
    }

    public void readOperationNumber(Opcode code, DataInputStream dis, boolean isWide) throws IOException {
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
                break;
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
                    this.skipCount += 2;
                    this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);

                } else {
                    this.skipCount += 2;
                    this.allOperationNumber.add(dis.readUnsignedByte() & 0xFF);
                }
                break;
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
                this.skipCount += 2;
                this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                break;
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
                this.skipCount += 2;
                this.allOperationNumber.add(dis.readShort() & 0xFFFF);
                break;
            case GOTO_W:
            case JSR_W:
                this.skipCount += 4;
                this.allOperationNumber.add(dis.readInt() & 0xFFFF);
                break;

            // subject to wide
            case IINC:
                if (isWide) {
                    this.skipCount += 4;
                    this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                    this.allOperationNumber.add(dis.readShort() & 0xFFFF);
                } else {
                    this.skipCount += 2;
                    this.allOperationNumber.add(dis.readUnsignedByte() & 0xFF);
                    this.allOperationNumber.add(dis.readByte() & 0xFF);
                }
                break;
            case TABLESWITCH:
                // skip padding
                int defaultOffset = dis.readInt();
                int low = dis.readInt();
                int high = dis.readInt();
                int[] offsets = new int[high - low + 1];
                for (int i = 0; i < offsets.length; i++) {
                    offsets[i] = dis.readInt();
                }
                this.skipCount += 12 + (high - low + 1) * 4;
                this.allOperationNumber.add(defaultOffset & 0xFFFF);
                this.allOperationNumber.add(low & 0xFFFF);
                this.allOperationNumber.add(high & 0xFFFF);
                for (int i = 0; i < offsets.length; i++) {
                    this.allOperationNumber.add(offsets[i] & 0xFFFF);
                }
                break;

            case LOOKUPSWITCH:
                // skip padding
                defaultOffset = dis.readInt();
                int npairs = dis.readInt();
                int[] matchOffsets = new int[npairs * 2];
                for (int i = 0; i < matchOffsets.length; i++) {
                    matchOffsets[i] = dis.readInt();
                }
                this.skipCount += 8 + npairs * 8;
                this.allOperationNumber.add(defaultOffset & 0xFFFF);
                this.allOperationNumber.add(npairs & 0xFFFF);
                for (int i = 0; i < matchOffsets.length; i++) {
                    this.allOperationNumber.add(matchOffsets[i] & 0xFFFF);
                }
                break;
            case INVOKEINTERFACE:
                this.skipCount += 4;
                this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                this.allOperationNumber.add(dis.readUnsignedByte() & 0xFF);
                this.allOperationNumber.add(dis.readUnsignedByte() & 0xFF);
                break;
            case INVOKEDYNAMIC:
                this.skipCount += 4;
                this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                break;

            case MULTIANEWARRAY:
                this.skipCount += 3;
                this.allOperationNumber.add(dis.readUnsignedShort() & 0xFFFF);
                this.allOperationNumber.add(dis.readUnsignedByte() & 0xFF);
                break;
        }
    }

}
