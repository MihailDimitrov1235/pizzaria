/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author mihailvd
 */
public class musicPlayer {
    public void play(InputStream inputStream) {
    try {
        AudioInputStream soundIn = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream) );
        AudioFormat format = soundIn.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(soundIn);
        clip.start();
        sleep(clip.getMicrosecondLength() / 1000);// Thread.yield();
    } catch (Exception e) {
        
        e.printStackTrace();
    }
}

private void sleep(long sleep) {
    try {
        Thread.sleep(sleep);
    } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
}
}
