package org.designpattern.visitor;

public interface ViewVisitor {

    String visit(Bus bus);


    String visit(Truck truck);

}
