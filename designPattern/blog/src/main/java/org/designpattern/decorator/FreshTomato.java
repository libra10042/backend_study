package org.designpattern.decorator;

public class FreshTomato extends ToppingDecorator {

    public FreshTomato(final Pizza pizza){
        super(pizza);
    }


    @Override
    public String getDescription() {
        return pizza.description + "wtih FreshTomato";
    }

    @Override
    public int getCost() {
        return 300 + pizza.getCost();
    }
}
