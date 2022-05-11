package org.designpattern.decorator;

public class CheesePizza extends Pizza{

    public CheesePizza(){
        this.description = "CheesePizza";
    }


    @Override
    public int getCost() {
        return 10_000;
    }
}
