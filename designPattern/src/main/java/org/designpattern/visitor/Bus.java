package org.designpattern.visitor;

public class Bus implements Car {

    private int fuel;

    public Bus(final int fuel){
        this.fuel = fuel;
    }


    public int drive() {
        return --fuel;
    }

    public int getFuel() {
        return fuel;
    }

    public String visit(final ViewVisitor viewVisitor) {

        return viewVisitor.visit(this);
    }


}
