package org.designpattern.command;

public class MusicOnCommand implements Command{
    private final Music music;

    MusicOnCommand(final Music music){
        this.music = music;
    }

    public String execute(){
        return music.on();
   }
}
