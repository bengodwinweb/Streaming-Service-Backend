package com.streamingserviceserver.util;

public class StringUtil {

    public static String normalizeStringForQuery(String s) {
        return s.replaceAll("[\"'/+()*<>\\[\\]`~\\-_{}]", " ").replaceAll("[!,.:;=?|]", "").toLowerCase();
    }
}
