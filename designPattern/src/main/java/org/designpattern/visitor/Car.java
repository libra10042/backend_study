package org.designpattern.visitor;

public interface Car {

    int drive();

    int getFuel();

    String visit(ViewVisitor viewVisitor);
}
