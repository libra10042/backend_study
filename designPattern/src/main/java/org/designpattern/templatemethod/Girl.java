package org.designpattern.templatemethod;

public class Girl extends Person {
    protected void makeUp(){
        System.out.println("makeUp : 씻고, 화장을 하자!");
        System.out.println("makeUp : 머리 말리는데 왜 이렇게 오래 걸리니ㅜㅜ");
    }

    void dressUp(){
        super.dressUp();
        System.out.println("dressUp : 치마를 입고, 블라우스를 입자!!");
        System.out.println("dressUp : 머리 점검, 화장 점검, 신발을 어떤거 신지?");
    }
}
