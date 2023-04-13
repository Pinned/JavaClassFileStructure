package com.example.clazz.attributes.code;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class JavaVirtualMachineCode {


    public static String readByteCode(byte[] code) {
        StringBuilder sb = new StringBuilder();
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(code));
        try {
            int index = 0;
            while (dis.available() > 0) {
                MachineCode machineCode = MachineCode.readMachineCode(index, dis);
                sb.append(machineCode.getShowInfo());
                index += machineCode.getSkipCount();
                index++;
            }
        } catch (Exception e) {
        }

        return sb.toString();
    }


}
