package org.designpattern.composite;

abstract class CafeComposite {
    private final String name;
    private final int price;

    CafeComposite(final String name, final int price){
        if(price < 0){
            throw new IllegalArgumentException("가격은 0원 이상이어여 한다.");
        }
        this.name = name;
        this.price = price;
    }
    String getName(){
        return name;
    }

    int getPrice(){
        return price;
    }

    public abstract String typeOf();

}
