package com.example.clazz.attributes.innerclass;

import com.example.clazz.utils.ClassAccessFlagsUtil;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class InnerClass {

    //    {   u2 inner_class_info_index;
//        u2 outer_class_info_index;
//        u2 inner_name_index;
//        u2 inner_class_access_flags;
//    }
    public int innerClassInfoIndex;
    public int outerClassInfoIndex;
    public int innerNameIndex;
    public int innerClassAccessFlags;


    public InnerClass(DataInputStream dis) throws IOException {
        this.innerClassInfoIndex = dis.readUnsignedShort();
        this.outerClassInfoIndex = dis.readUnsignedShort();
        this.innerNameIndex = dis.readUnsignedShort();
        this.innerClassAccessFlags = dis.readUnsignedShort();
    }

    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        ArrayDotItem arrayDotItem = new ArrayDotItem("elements", index, "")
                .parent(parent).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        if (innerClassInfoIndex > 0) {
            arrayDotItem.addChild("inner", classDot.getConstantItem(innerClassInfoIndex));
        }
        if (outerClassInfoIndex > 0) {
            arrayDotItem.addChild("outer", classDot.getConstantItem(outerClassInfoIndex));
        } else {
            arrayDotItem.addChild("outer", new DotItem("outer", "无")
                    .parent(arrayDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED));
        }
        if (innerNameIndex > 0) {
            arrayDotItem.addChild("name", classDot.getConstantItem(innerNameIndex));
        } else {
            arrayDotItem.addChild("name", new DotItem("name", "无")
                    .parent(arrayDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED));
        }

        DotItem accessFlag = new DotItem("access_flag", ClassAccessFlagsUtil.getAccessFlagDetail(innerClassAccessFlags))
                .parent(arrayDotItem).shape(DotShape.BOX);
        arrayDotItem.addChild("access", accessFlag);
        return arrayDotItem;
    }

}
