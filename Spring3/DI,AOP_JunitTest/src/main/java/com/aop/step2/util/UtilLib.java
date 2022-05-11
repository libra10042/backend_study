package com.aop.step2.util;

public class UtilLib {

    public static String lpad(String str, int len, String addStr){
        String result = str;
        int templen = len - result.length();

        for(int i=0; i<templen; i++){
            result = addStr + result;
        }
        return result;
    }
}
