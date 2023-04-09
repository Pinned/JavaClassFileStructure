package com.example.clazz.attributes;

import com.example.clazz.attributes.AttributeVerbose;
import com.example.clazz.attributes.annotation.ParameterAnnotationsAttributeVerbose;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RuntimeVisibleParameterAnnotationsAttributeVerbose extends ParameterAnnotationsAttributeVerbose {

    public RuntimeVisibleParameterAnnotationsAttributeVerbose(ClassDot classDot, int attributeNameIndex, DataInputStream dis) throws IOException {
        super(classDot, attributeNameIndex, dis);
    }
}
