package org.designpattern.templatecallback;

public class Player {
    public String play(final RockScissorPaper strategy){
        return strategy.submit();
    }
}
