package ww;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class testtttt {
	void please() {
		try {
		float sampleRate = 8000;
		int sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format =  new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
		line.open(format);
		line.start();
		System.out.println("start recoding");
		int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
		byte buffer[] = new byte[bufferSize];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		 long startTime = System.currentTimeMillis();
		 long estimatedTime = System.currentTimeMillis() - startTime;
		while (estimatedTime < 6000) {
				  int count = line.read(buffer, 0, buffer.length);
				  if (count > 0) {
				    out.write(buffer, 0, count);
				  }
				  estimatedTime = System.currentTimeMillis() - startTime;
		}
		out.close();
		System.out.println("stop recoding");
		
		byte audio[] = out.toByteArray();
		InputStream input = new ByteArrayInputStream(audio);
		AudioInputStream ais = new AudioInputStream(input, format, audio.length / format.getFrameSize());
		DataLine.Info info1 = new DataLine.Info(SourceDataLine.class, format);
		SourceDataLine line1 = (SourceDataLine)AudioSystem.getLine(info1);
		line1.open(format);
		line1.start();
		
		int bufferSize1 = (int) format.getSampleRate() * format.getFrameSize();
				byte buffer1[] = new byte[bufferSize1];
				 
				int count;
				while ((count = 
				    ais.read(buffer1, 0, buffer1.length)) != -1) {
				  if (count > 0) {
				    line1.write(buffer1, 0, count);
				  }
				}
				line1.drain();
				line1.close();
		
				System.out.println("end");
				
		} catch(Exception e) {
			
		}
		
	}
}
