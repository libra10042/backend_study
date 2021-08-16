package org.designpattern.composite;

public class Desk extends CafeComposite{

    public Desk(final String name, int price) {
        super(name, price);
    }

    @Override
    public String typeOf() {
        return "Desk";
    }
}
