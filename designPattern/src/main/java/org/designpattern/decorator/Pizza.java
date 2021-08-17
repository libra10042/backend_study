package org.designpattern.decorator;

public abstract class Pizza {
    protected String description = "Original Pizza";

    public String getDescription(){
        return "description";
    }

    public abstract int getCost();

}
