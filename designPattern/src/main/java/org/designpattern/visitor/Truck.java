package org.designpattern.visitor;

public class Truck implements Car {

    private int fuel;

    public int drive() {
        return --fuel;
    }

    public int getFuel() {
        return fuel;
    }

    public String visit(ViewVisitor viewVisitor) {
        return null;
    }
}
