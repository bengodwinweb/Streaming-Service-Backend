package com.streamingserviceserver.util;

public class BooleanUtil {

    public static boolean toBool(int n) {
        return n != 0;
    }

    public static int toInt(boolean b) {
        return b ? 1 : 0;
    }
}
