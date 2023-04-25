package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Base64;

public class Utf8ConstantVerbose implements ConstantVerbose {
    private String value;

    public Utf8ConstantVerbose(String value) {
        this.value = value;
    }

    public Utf8ConstantVerbose(DataInputStream dis) throws IOException {
        int length = dis.readUnsignedShort();
        byte[] bytes = new byte[length];
        dis.read(bytes);
        if (isUtf8(bytes)) {
            value = new String(bytes, "UTF-8");
        } else {
            value = Base64.getEncoder().encodeToString(bytes);
        }
    }

    public boolean isUtf8(byte[] bytes) {
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        try {
            decoder.decode(ByteBuffer.wrap(bytes));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getSkipCount() {
        return 0;
    }

    @Override
    public void write(Integer tag, DataOutputStream dos) {
        try {
            dos.writeByte(tag & 0xFF);
            dos.writeShort(value.length() & 0xFFFF);
            dos.write(value.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
