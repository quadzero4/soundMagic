package ww;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class sound {

   // Constructor
   public sound() {          
      try {
         // Open an audio input stream.           
          File soundFile = new File("D:\\Java\\workspace\\TEST\\src\\ww\\wave_guitar.wav"); //you could also get the sound file with an URL
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); 
          AudioFormat format = audioIn.getFormat();             
         // Get a sound clip resource.
         DataLine.Info info = new DataLine.Info(Clip.class, format);
         Clip clip = (Clip)AudioSystem.getLine(info);
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      new sound();
      System.out.println("gg");
   }
}

