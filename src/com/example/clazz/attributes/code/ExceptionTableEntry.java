package com.example.clazz.attributes.code;

import com.example.clazz.dot.*;

public class ExceptionTableEntry {
    public int startPc;
    public int endPc;
    public int handlerPc;
    public int catchType;

    public DotItem createDotItem(ClassDot classDot, DotItem superDotItem, int i) {
        ArrayDotItem exceptionTableEntryDotItem = new ArrayDotItem("exception_table_entry", i, "")
                .parent(superDotItem).shape(DotShape.CIRCLE).style(DotStyle.DASHED);
        DotItem startPcDotItem = new DotItem("start_pc", String.valueOf(startPc), exceptionTableEntryDotItem);
        exceptionTableEntryDotItem.addChild("start_pc", startPcDotItem);
        DotItem endPcDotItem = new DotItem("end_pc", String.valueOf(endPc), exceptionTableEntryDotItem);
        exceptionTableEntryDotItem.addChild("end_pc", endPcDotItem);
        DotItem handlerPcDotItem = new DotItem("handler_pc", String.valueOf(handlerPc), exceptionTableEntryDotItem);
        exceptionTableEntryDotItem.addChild("handler_pc", handlerPcDotItem);
        exceptionTableEntryDotItem.addChild("catch_type", classDot.getConstantItem(catchType));
        return exceptionTableEntryDotItem;
    }
}
