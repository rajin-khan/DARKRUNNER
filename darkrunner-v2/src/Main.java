import java.io.*;
import javax.sound.sampled.*; //for the music player

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        File file = new File("deadmau5_Strobe_radioedit.wav"); //creates and opens the .wav file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); //sets up an audiostream object using the wav file (basically preparing an audio stream)

        Clip clip = AudioSystem.getClip(); //creates a playable clip object from the audisystem class
        clip.open(audioStream); //opens the audio stream as a playable clip

        clip.start(); //the clip is started when we hit run, and goes on for as long as the game is running, until the GUI window is closed.
        System.out.println("\nMusic Started.");

        new LaunchPage();
        System.out.println("Launch Window initiated.");
    }
    
}