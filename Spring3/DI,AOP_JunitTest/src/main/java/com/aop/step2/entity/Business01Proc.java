package com.aop.step2.entity;


import org.springframework.stereotype.Component;

@Component
public class Business01Proc {

    public Business01Proc() {

    }

    public String cmdComponent01(){
        System.out.println("Business01Proc => cmdComponent01");
        return "RETRUN : Business01Proc => cmdComponent01 => " + "No Parameter\n";
    }

    public String cmdComponent02(String str, int a, int b){
        System.out.println("Business01Proc => cmdComponent02");
        return "RETURN : Business01Proc => cmdComponent02 => Parameter : " + (a + b) + "\n";
    }


}
