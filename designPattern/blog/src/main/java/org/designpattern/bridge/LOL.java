package org.designpattern.bridge;

public class LOL implements PlayGameAPI {


    @Override
    public String playGame(final String id, final String password) {
        return "LOL Connect completed1[id=" + id + " password=" + password + "]";
    }
}
