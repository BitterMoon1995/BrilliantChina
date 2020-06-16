package com.zh.admin.utils;

import java.util.Random;

public class RandomCode {
    //指定长度字母+数字随机数
    public static String getCharAndNum(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            builder.append(charStr.charAt(index));
        }
        return builder.toString();
    }
}
