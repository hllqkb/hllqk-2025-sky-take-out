package com.sky.encryption;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
@Component
public class Myencryption {
    public static String encrypt(String str) {
        str= DigestUtils.md5DigestAsHex(str.getBytes());
        return str;
    }
}
