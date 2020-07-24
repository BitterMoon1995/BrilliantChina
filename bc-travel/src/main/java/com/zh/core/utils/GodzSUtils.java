package com.zh.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Random;

public class GodzSUtils {
    //指定长度字母+数字随机数
    public static String getCharAndNum(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charStr.length());
            builder.append(charStr.charAt(index));
        }
        return builder.toString();
    }

    //返回中文字符串的首字母
    public static String getFirstPinYin(String hanyu) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//输出大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//没有音标

        StringBuilder firstPinyin = new StringBuilder();
        char[] hanyuArr = hanyu.trim().toCharArray();
        try {
            for (char c : hanyuArr) {
                //判断是否中文，若非，直接append
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] pys = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    //多音字取第一个
                    firstPinyin.append(pys[0].charAt(0));
                } else {
                    firstPinyin.append(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return firstPinyin.toString();
    }
}
