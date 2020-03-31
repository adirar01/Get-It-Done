package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayMusic {

    /*
     * EFFECTS: plays the zelda.wav sound clip from data folder
     * */
    static void playSound(String s) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        /*
         * The following code is adapted from:
         * https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
         */
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
