package org.designpattern.templatemethod;

// 추상클래스는 상속을 강제하기 위한 것이다.
public abstract class Person {

    //Template Method : 사람들이 여행 준비를 하는 로직을 나타내는 메서드이다.
    public void readyToTrip(){
        makeUp();
        dressUp();
        System.out.println("ready : 밖으로 나가자!");
        System.out.println("ready : 여행 준비가 끝났어!!");
    }

    // Hook Method : hook method로 override를 해도 되고, 안해도 된다. !
    void dressUp(){
        System.out.println("dressUp : 속옷을 입자!");
    }

    //abstract method : makeUp메서드는 사람에 따라서 준비과정이 다르므로 추상화를 한다.
    protected abstract void makeUp();

}
