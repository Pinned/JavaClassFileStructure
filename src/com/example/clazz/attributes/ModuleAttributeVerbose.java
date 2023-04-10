package com.example.clazz.attributes;

import com.example.clazz.ModuleAccessFlagsUtil;
import com.example.clazz.attributes.module.Exports;
import com.example.clazz.attributes.module.Opens;
import com.example.clazz.attributes.module.Provides;
import com.example.clazz.attributes.module.Requires;
import com.example.clazz.dot.ClassDot;
import com.example.clazz.dot.DotItem;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleAttributeVerbose extends AttributeVerbose {
    public int moduleNameIndex;
    public int moduleFlags;
    public int moduleVersionIndex;
    public int requiresCount;
    public Requires[] requires;
    public int exportsCount;
    public Exports[] exports;
    public int opensCount;
    public Opens[] opens;
    public int usesCount;
    public int[] uses;
    public int providesCount;
    public Provides[] provides;

    public ModuleAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
        moduleNameIndex = dis.readUnsignedShort();
        moduleFlags = dis.readUnsignedShort();
        moduleVersionIndex = dis.readUnsignedShort();
        requiresCount = dis.readUnsignedShort();
        requires = new Requires[requiresCount];
        for (int i = 0; i < requiresCount; i++) {
            requires[i] = new Requires(classDot, dis);
        }
        exportsCount = dis.readUnsignedShort();
        exports = new Exports[exportsCount];
        for (int i = 0; i < exportsCount; i++) {
            exports[i] = new Exports(classDot, dis);
        }
        opensCount = dis.readUnsignedShort();
        opens = new Opens[opensCount];
        for (int i = 0; i < opensCount; i++) {
            opens[i] = new Opens(classDot, dis);
        }
        usesCount = dis.readUnsignedShort();
        uses = new int[usesCount];
        for (int i = 0; i < usesCount; i++) {
            uses[i] = dis.readUnsignedShort();
        }
        providesCount = dis.readUnsignedShort();
        provides = new Provides[providesCount];
        for (int i = 0; i < providesCount; i++) {
            provides[i] = new Provides(classDot, dis);
        }
    }

    @Override
    public DotItem createDotItem(ClassDot classDot, DotItem parent, int index) {
        DotItem superDotItem = super.createDotItem(classDot, parent, index);
        superDotItem.addChild("moduleNameIndex", classDot.getConstantItem(moduleNameIndex));
        DotItem accessFlag = new DotItem("moduleFlags", ModuleAccessFlagsUtil.getAccessFlagDetail(moduleFlags))
                .parent(superDotItem);
        superDotItem.addChild("moduleFlags", accessFlag);
        superDotItem.addChild("moduleVersionIndex", classDot.getConstantItem(moduleVersionIndex));
        DotItem requiresDotItem = new DotItem("requires", String.valueOf(requiresCount))
                .parent(superDotItem);
        superDotItem.addChild("requires", requiresDotItem);
        for (int i = 0; i < requiresCount; i++) {
            requiresDotItem.addChild("", requires[i].createDotItem(classDot, requiresDotItem, i));
        }
        DotItem exportsDotItem = new DotItem("exports", String.valueOf(exportsCount), superDotItem);
        superDotItem.addChild("exports", exportsDotItem);
        for (int i = 0; i < exportsCount; i++) {
            exportsDotItem.addChild("", exports[i].createDotItem(classDot, exportsDotItem, i));
        }
        DotItem opensDotItem = new DotItem("opens", String.valueOf(opensCount), superDotItem);
        superDotItem.addChild("opens", opensDotItem);
        for (int i = 0; i < opensCount; i++) {
            opensDotItem.addChild("", opens[i].createDotItem(classDot, opensDotItem, i));
        }
        DotItem usesDotItem = new DotItem("uses", String.valueOf(usesCount), superDotItem);
        superDotItem.addChild("uses", usesDotItem);
        for (int i = 0; i < usesCount; i++) {
            usesDotItem.addChild("", classDot.getConstantItem(uses[i]));
        }
        DotItem providesDotItem = new DotItem("provides", String.valueOf(providesCount), superDotItem);
        superDotItem.addChild("provides", providesDotItem);
        for (int i = 0; i < providesCount; i++) {
            providesDotItem.addChild("", provides[i].createDotItem(classDot, providesDotItem, i));
        }
        return superDotItem;
    }
}
