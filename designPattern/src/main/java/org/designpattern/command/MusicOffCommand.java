package org.designpattern.command;

public class MusicOffCommand implements Command{
    private final Music music;

    MusicOffCommand(final Music music){
        this.music = music;
    }


    public String execute(){
        return music.off();
    }

}
