/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author mihailvd
 */
public class musicPlayer {
    private Clip clip;

    public musicPlayer() {
        // Initialize the music player here
        String filePath = "music.wav";
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInput);
            } else {
                System.out.println("music not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void play() {
        if (this.clip != null) {
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (this.clip != null) {
            this.clip.stop();
        }
    }
}
