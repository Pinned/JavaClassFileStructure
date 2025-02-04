package com.example.clazz.format;

abstract class ClassFormat implements GenericInterface<String> {
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

    private String innerStr = "this is inner string.";

    public ClassFormat() {
    }

    public void testMethod() {
        TestInterface intVerbos = new TestInterface() {
            @Override
            public void testMethodV1() {
                System.out.println(innerStr);
            }

            @Override
            public void testMethodV2() {

            }
        };

    }

    public void thisIsPublicMethod() {
        System.out.println("This is a public method");
    }

    protected void thisIsProtectedMethod() {
        System.out.println("This is a protected method");
    }

    private void thisIsPrivateMethod() {
        System.out.println("This is a private method");
    }

    public final void thisIsFinalMethod() {
        System.out.println("This is a final method");
    }

    public static void thisIsStaticMethod() {
        System.out.println("This is a static method");
    }

    public static final void thisIsStaticFinalMethod() {
        System.out.println("This is a static final method");
    }

    public void testVarargsMethod(String... args) {
        System.out.println("This is a varargs method");
    }

    public strictfp void testStrictfpMethod(double a) {
        System.out.println("This is a strictfp method");
    }

    public abstract void testAbstractMethod();

    public native void testNativeMethod();

    @Override
    public String testBridgeMethod() {
        return null;
    }
}




