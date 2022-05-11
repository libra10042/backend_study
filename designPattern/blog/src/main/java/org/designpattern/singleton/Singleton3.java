package org.designpattern.singleton;

public enum Singleton3 {
    INSTANCE;

    private final String name = "This is JDBC Driver";

    public String getName(){
        return name;
    }

}
