package org.designpattern.visitor;

public class Truck implements Car {

    private int fuel;

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
