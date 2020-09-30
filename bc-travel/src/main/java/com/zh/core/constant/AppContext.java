package com.zh.core.constant;

public class AppContext {

    public static final String TOKEN = "LinkAdmin-Token";

    public static final String Department_Key = "department_key";
    // ok
    public static final int CODE_20000 = 20000;
    // error
    public static final int CODE_50000 = 50000;
    // Illegal token
    public static final int CODE_50001 = 50001;
    // no permission
    public static final int CODE_50002 = 50002;
    // DemoSystem
    public static final int CODE_50003 = 50003;
    //service reject request
    public static final int CODE_50004 = 50004;

    public static void main(String[] args) {
        String s = "http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AIOwIAA75dSP4bv4227.png";
        String pre = "/usr/storage/data/";
        System.out.println(pre+s.substring(35));
    }
}
