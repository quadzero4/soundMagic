package pleaseLast;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Freq {
	
	public static float SAMPLE_RATE = 8000f;

	private AudioFormat af;
	private SourceDataLine sdl;
	
	// Constructor
	public Freq() throws LineUnavailableException {
		af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
		sdl = AudioSystem.getSourceDataLine(af);
	}
	
	public void tone(double hz, double ms) throws LineUnavailableException{
		tone(hz, ms, 1.0);
	}
	
	public void tone(double hz, double ms, double vol) throws LineUnavailableException {
		byte[] buf = new byte[1];
		sdl.open(af);
		sdl.start();
		
		// Speicify playback time from SAMPLE_RATE
		int sizePerMs = (int)(SAMPLE_RATE / 1000);
		
		for(int i = 0; i < ms * sizePerMs; i++) {
			double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
			sdl.write(buf, 0, 1);
		}
		
		sdl.drain();
		
	}
	
	public void tones(List<Double> freqs, double ms) throws LineUnavailableException {
		tones(freqs, ms, 1.0);
	}
	
	public void tones(List<Double> freqs, double ms, double vol) throws LineUnavailableException {
		byte[] buf = new byte[1];
		sdl.open(af);
		sdl.start();
		
	// Speicify playback time from SAMPLE_RATE
		int sizePerMs = (int)(SAMPLE_RATE / 1000);
		
		for(double hz : freqs) {
			for(int i = 0; i < ms * sizePerMs; i++) {
				double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
				sdl.write(buf, 0, 1);
			}
		}

		sdl.drain();
	}
	
	public void close() {
		sdl.stop();
		sdl.close();
	}
	
	
}