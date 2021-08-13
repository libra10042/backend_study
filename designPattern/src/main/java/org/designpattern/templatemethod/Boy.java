package org.designpattern.templatemethod;

public class Boy extends Person{
    protected void makeUp(){
        System.out.println("makeUp : 씻고, 옷입고, 거울 보고 끝!");
    }

    void dressUp(){
        super.dressUp();
        System.out.println("dressUp : 반팔, 긴바지, 시계를 차자!");
    }
}
