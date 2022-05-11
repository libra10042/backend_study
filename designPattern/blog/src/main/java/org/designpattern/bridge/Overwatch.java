package org.designpattern.bridge;

public class Overwatch implements PlayGameAPI {

    @Override
    public String playGame(final String id, final String password) {
        return "Overwatch Connect complete! [id=" + id + " password=" + password +"]";
    }
}
