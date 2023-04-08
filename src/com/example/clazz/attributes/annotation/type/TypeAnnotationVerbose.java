package com.example.clazz.attributes.annotation.type;

import com.example.clazz.attributes.annotation.AnnotationElementValuePair;
import com.example.clazz.dot.*;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeAnnotationVerbose {
    //    type_annotation {
//        u1 target_type;
//        union {
//            type_parameter_target;
//            supertype_target;
//            type_parameter_bound_target;
//            empty_target;
//            formal_parameter_target;
//            throws_target;
//            localvar_target;
//            catch_target;
//            offset_target;
//            type_argument_target;
//        } target_info;
//        type_path target_path;
//        u2        type_index;
//        u2        num_element_value_pairs;
//        {   u2            element_name_index;
//            element_value value;
//        } element_value_pairs[num_element_value_pairs];
//    }
    public int targetType;
    public TypeParameterTarget typeParameterTarget;
    public SupertypeTarget supertypeTarget;
    public TypeParameterBoundTarget typeParameterBoundTarget;
    public EmptyTarget emptyTarget;
    public FormalParameterTarget formalParameterTarget;
    public ThrowsTarget throwsTarget;
    public LocalvarTarget localvarTarget;
    public CatchTarget catchTarget;
    public OffsetTarget offsetTarget;
    public TypeArgumentTarget typeArgumentTarget;
    public TypePath typePath;
    public int typeIndex;
    public int numElementValuePairs;
    public AnnotationElementValuePair[] elementValuePairs;

    public TypeAnnotationVerbose(DataInputStream dis) throws IOException {
        targetType = dis.readUnsignedByte();
        switch (targetType) {
            case 0x00:
            case 0x01:
                typeParameterTarget = new TypeParameterTarget(dis);
                break;
            case 0x10:
                supertypeTarget = new SupertypeTarget(dis);
                break;
            case 0x11:
            case 0x12:
                typeParameterBoundTarget = new TypeParameterBoundTarget(dis);
                break;
            case 0x13:
            case 0x14:
                emptyTarget = new EmptyTarget(dis);
                break;
            case 0x16:
                formalParameterTarget = new FormalParameterTarget(dis);
                break;
            case 0x17:
                throwsTarget = new ThrowsTarget(dis);
                break;
            case 0x40:
            case 0x41:
                localvarTarget = new LocalvarTarget(dis);
                break;
            case 0x42:
                catchTarget = new CatchTarget(dis);
                break;
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46:
                offsetTarget = new OffsetTarget(dis);
                break;
            case 0x47:
            case 0x48:
            case 0x49:
            case 0x4A:
            case 0x4B:
                typeArgumentTarget = new TypeArgumentTarget(dis);
                break;
            default:
                throw new RuntimeException("Unknown target type: " + targetType);
        }
        typePath = new TypePath(dis);
        typeIndex = dis.readUnsignedShort();
        numElementValuePairs = dis.readUnsignedShort();
        elementValuePairs = new AnnotationElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new AnnotationElementValuePair();
            elementValuePairs[i].read(dis);
        }

    }


    public String getReadableTargetType() {
        switch (targetType) {
            case 0x00:
                return "declaration of generic class or interface";
            case 0x01:
                return "declaration of generic method or constructor";
            case 0x10:
                return "extends or implements clause of class declaration (including the direct superclass or direct superinterface of an anonymous class declaration), or in extends clause of interface declaration";
            case 0x11:
                return "bound of type parameter declaration of generic class or interface";
            case 0x12:
                return "bound of type parameter declaration of generic method or constructor";
            case 0x13:
                return "field or record component declaration";
            case 0x14:
                return "return type of method, or type of newly constructed object";
            case 0x15:
                return "receiver type of method or constructor";

            case 0x16:
                return "type in formal parameter declaration of method, constructor, or lambda expression";
            case 0x17:
                return "type in throws clause of method or constructor";
            case 0x40:
                return "local variable declaration";
            case 0x41:
                return "resource variable declaration";
            case 0x42:
                return "exception parameter declaration";
            case 0x43:
                return "instanceof expression";
            case 0x44:
                return "new expression";
            case 0x45:
                return "method reference expression using ::new";
            case 0x46:
                return "method reference expression using ::Identifier";
            case 0x47:
                return "cast expression";
            case 0x48:
                return "for generic constructor in new expression or explicit constructor invocation statement";
            case 0x49:
                return "for generic method in method invocation expression";
            case 0x4A:
                return "for generic constructor in method reference expression using ::new";
            case 0x4B:
                return "for generic method in method reference expression using ::Identifier";
            default:
                throw new RuntimeException("Unknown target type: " + targetType);
        }
    }

    public DotItem createDotItem(ClassDot classDot, DotItem superDotItem, int index) {
        ArrayDotItem typeAnnotationDot = new ArrayDotItem("type_annotation", index, "")
                .parent(superDotItem).style(DotStyle.DASHED).shape(DotShape.CIRCLE);
        typeAnnotationDot.addChild("targetType", new DotItem("targetType", "0x" + Integer.toHexString(targetType) + "\\n" + getReadableTargetType()).shape(DotShape.BOX));

        switch (targetType) {
            case 0x00:
            case 0x01:
                typeAnnotationDot.addChild("type_param", typeParameterTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x10:
                typeAnnotationDot.addChild("super_type", supertypeTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x11:
            case 0x12:
                typeAnnotationDot.addChild("type_param_bound", typeParameterBoundTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x13:
            case 0x14:
                typeAnnotationDot.addChild("empty", emptyTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x16:
                typeAnnotationDot.addChild("formal_param", formalParameterTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x17:
                typeAnnotationDot.addChild("throws", throwsTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x40:
            case 0x41:
                typeAnnotationDot.addChild("localvar", localvarTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x42:
                typeAnnotationDot.addChild("catch", catchTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46:
                typeAnnotationDot.addChild("offset", offsetTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            case 0x47:
            case 0x48:
            case 0x49:
            case 0x4A:
            case 0x4B:
                typeAnnotationDot.addChild("type_arg", typeArgumentTarget.createDotItem(classDot, typeAnnotationDot, 0));
                break;
            default:
                throw new RuntimeException("Unknown target type: " + targetType);
        }
        typeAnnotationDot.addChild("type_path", typePath.createDotItem(classDot, typeAnnotationDot, 0));
        typeAnnotationDot.addChild("type", classDot.getConstantItem(typeIndex));
        typeAnnotationDot.addChild("num_element_value_pairs", new DotItem("num_element_value_pairs", String.valueOf(numElementValuePairs)));
        for (int i = 0; i < numElementValuePairs; i++) {
            typeAnnotationDot.addChild("", elementValuePairs[i].createDotItem(classDot, typeAnnotationDot, i));
        }
        return typeAnnotationDot;
    }
}
