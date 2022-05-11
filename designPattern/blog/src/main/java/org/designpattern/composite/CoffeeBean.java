package org.designpattern.composite;

public class CoffeeBean extends CafeComposite {

    public CoffeeBean(final String name, final int price) {
        super(name, price);
    }

    @Override
    public String typeOf() {
        return "CoffeBean";
    }
}
