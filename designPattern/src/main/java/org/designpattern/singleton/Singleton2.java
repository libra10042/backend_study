package org.designpattern.singleton;

public class Singleton2 {

    private static volatile Singleton2 instance = new Singleton2();

    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }


}
