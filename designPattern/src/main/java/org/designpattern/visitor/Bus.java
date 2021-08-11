package org.designpattern.visitor;

public class Bus implements Car {

    private int fuel;

    public Bus(final int fuel){
        this.fuel = fuel;
    }


    public int drive() {
        return 0;
    }

    public int getFuel() {
        return 0;
    }

    public String visit(ViewVisitor viewVisitor) {
        return null;
    }


}
