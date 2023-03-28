package com.example.clazz.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantVerboseFactory {
    public static ConstantVerbose createConstant(int tag, DataInputStream dis) throws IOException {
        switch (tag) {
            case 1:
                return new Utf8ConstantVerbose(dis);
            case 3:
                return new IntegerConstantVerbose(dis);
            case 4:
                return new FloatConstantVerbose(dis);
            case 5:
                return new LongConstantVerbose(dis);
            case 6:
                return new DoubleConstantVerbose(dis);
            case 7:
                return new ClassConstantVerbose(dis);
            case 8:
                return new StringConstantVerbose(dis);
            case 9:
                return new FieldRefConstantVerbose(dis);
            case 10:
                return new MethodRefConstantVerbose(dis);
            case 11:
                return new InterfaceMethodRefConstantVerbose(dis);
            case 12:
                return new NameAndTypeConstantVerbose(dis);
            case 15:
                return new MethodHandleConstantVerbose(dis);
            case 16:
                return new MethodTypeConstantVerbose(dis);
            case 17:
                return new DynamicConstantVerbose(dis);
            case 18:
                return new InvokeDynamicConstantVerbose(dis);
            case 19:
                return new ModuleConstantVerbose(dis);
            case 20:
                return new PackageConstantVerbose(dis);
            default:
                throw new RuntimeException("不支持的常量池类型 tag：" + tag + " b:" + Integer.toBinaryString(tag));
        }
    }
}
