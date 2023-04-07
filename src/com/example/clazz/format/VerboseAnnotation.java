package com.example.clazz.format;


@IntRange(from = 80, to = 90)
@Deprecated
public class VerboseAnnotation {

    @Deprecated
    public int deprecatedVerbose = 1;

    @IntRange(from = 1, to = 10)
    public int value = 32767;

    @Deprecated
    public void setValue(@IntRange(from = 40, to = 60) int value, @IntRange(from = 20, to = 30) int other) {
        this.value = value;
    }
}