package edu.hitsz.music;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SoundThread extends MusicThread{
    public SoundThread(String filename){
        super(filename);
    }
    @Override
    public void run(){
        InputStream stream = new ByteArrayInputStream(this.samples);
        play(stream);
    }
}
