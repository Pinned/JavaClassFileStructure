package com.example.clazz;

import java.io.File;

class ClassFormat {
    public int verbose = 32767;
    public int verboseV2 = 32768;
    public int verboseV3 = 4;
    public double doubleVerbose = 1;
    public double doubleVerboseV2 = 1.1;
    public float floatVerbose = 1f;
    public float floatVerboseV2 = 1.00001f;
    public long longVerbose = 1L;
    public long longVerboseV2 = 2L;
    public char charVerbose = 'Z';
    public transient String initStr = "This is a String";
    public TestEnum eum1;

    public ClassFormat() {
    }

    public void testMethod() {
        TestInterface intVerbos = new TestInterface() {
            @Override
            public void testMethodV1() {

            }

            @Override
            public void testMethodV2() {

            }
        };

    }
}

enum TestEnum {
    TEST_ENUM_V1,
    TEST_ENUM_V2
}

interface TestInterface {
    void testMethodV1();

    void testMethodV2();
}