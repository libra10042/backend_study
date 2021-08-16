package org.designpattern.composite;

public class Chair extends CafeComposite {
    public Chair(final String name, final int price){
        super(name, price);
    }
    public String typeOf(){
        return "Chair";
    }

}
