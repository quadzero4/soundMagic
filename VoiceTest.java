package pleaseLast;

import java.util.ArrayList;
import java.util.List;

import ss.pleasetell;

public class VoiceTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*Voiceget javaSoundRecorder;
		
		javaSoundRecorder  = new Voiceget();
		
		
        Thread thread = new Thread(javaSoundRecorder);
        thread.start();
        System.out.println("start");
        long startTime = System.currentTimeMillis();
		 long estimatedTime = System.currentTimeMillis() - startTime;
		while (estimatedTime < 6000) {
				  estimatedTime = System.currentTimeMillis() - startTime;
		}
		javaSoundRecorder.finish();
		*/
		Freq f = new Freq();
		
//		double a4 = 452;
//		for(int i = 1; i <= 16; i++) {
//			System.out.println(a4  / 6.727 * i);
//			f.tone(a4 / 6.727 * i, 500);
//		}
		/*
		System.out.println("== Microtonal Game (Ascending) ==\n");
		
		double startFreq = 329.63, // E4 ~ G4
				endFreq = 392;
		
		int startDiv = 2, endDiv = 8;
		
		for(int i = startDiv; i <= endDiv; i++) {
			System.out.printf("--[%d]--\n", i);
			
			double eachFreq = (endFreq - startFreq) / i;
			List<Double> freqs = new ArrayList<Double>();
			for(int j = 0; j <= i; j++) {
				double freq = startFreq + eachFreq * j;
				freqs.add(freq);
			}
			
			// Display each frequency on time
			new Thread(() -> {
				try {
					for(Double hz : freqs) {
						System.out.printf("%.2f\t", hz);
						Thread.sleep(200);
					}
				}catch(InterruptedException e) {}
			}).start();
			
			f.tones(freqs, 200);
			Thread.sleep(500);
			System.out.print("\n\n");
		}
		*/
		pleasetell tel = new pleasetell();
		List<Double> frefre = tel.freqVisualizer();
		
		for(int i = 0 ; i< frefre.size() ; i++) {
			System.out.println(frefre.get(i));
		}
	
	}

}
