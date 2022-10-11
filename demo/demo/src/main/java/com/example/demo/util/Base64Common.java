package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Common {
    public static String decodeBaseToString(String base64String) {
        byte[] base64 = base64String.getBytes(StandardCharsets.UTF_8);
        byte[] decode = Base64.getDecoder().decode(base64);
        return new String(decode, StandardCharsets.UTF_8);
    }
}
