package org.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subscriber implements Observable{

    private final List<Observer> observers = new ArrayList();
    private boolean newContent;


    public void makeNewContents() {
        newContent = true;
    }


    public boolean isNewContent(){
        return newContent;
    }

    // 구독자를 추가하는 메소드
    public void doSubscribe(final Observer observer) {
    observers.add(observer);
    }

    // 구독자를 삭제하는 메소드
    public void exitSubscribe(final Observer observer) {
        observers.remove(observer);
    }

    // 구독자들에게 update하는 메서드
    public void alarmToAllObservers() {
        observers.forEach(Observer::update);
    }


}
