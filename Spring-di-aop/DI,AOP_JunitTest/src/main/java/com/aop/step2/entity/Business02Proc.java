package com.aop.step2.entity;

import org.springframework.stereotype.Component;

@Component
public class Business02Proc {

    public Business02Proc(){}

    public String cdComponent03(){
        return "Business02Proc : cdComponent03 =>" + "NO Parameter";
    }


    public String cdComponent04(String str){
        return "Business02Proc : cdComponent04 => Parameter : " + str;

    }


    public void printThrowException(){
        throw new IllegalArgumentException(" => 고의로 발생시켰음");
        // 오류가 나면 상위한테 알려준다.
    }

}
