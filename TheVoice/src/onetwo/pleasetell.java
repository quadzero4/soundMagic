package onetwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ddf.minim.AudioSample;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;



public class pleasetell {
		
	
		public List<Double> freqVisualizer(){
			
			try {
				
				Minim minim = MinimImpl.getMinimInstance();
				AudioSample jingle = minim.loadSample("d:\\y2mate.com - maroon_5_memories_SlPhMPnQ58k.mp3", 2048);

				
				float[] leftChannel = jingle.getChannel(AudioSample.LEFT);
				System.out.println("channel length: " + leftChannel.length);
				System.out.println("length / 500: " + (leftChannel.length / 500) );
				System.out.println("near pow2: " + (tempPowerOfTwo(leftChannel.length / 500) ));

				// then we create an array we'll copy sample data into for the FFT object
				// this should be as large as you want your FFT to be. generally speaking, 1024 is probably fine.
				int fftSize = (int)(tempPowerOfTwo(leftChannel.length / 500));
				float[] fftSamples = new float[fftSize];
				System.out.println("sample rate: " + jingle.sampleRate());
				FFT fft = new FFT( fftSize, jingle.sampleRate() );

				// now analyze this buffer
				fft.forward( fftSamples );

				// now we'll analyze the samples in chunks
				int totalChunks = (leftChannel.length / fftSize) + 1;

				System.out.println("totalChunks: " + totalChunks);
				List<Double> outputFreqArr = new ArrayList<>();
				Map<String, Object> outputMap = new HashMap<String, Object>();


				// allocate a 2-dimensional array that will hold all of the spectrum data for all of the chunks.
				// the second dimension if fftSize/2 because the spectrum size is always half the number of samples analyzed.
				float[][] spectra = new float[totalChunks][fftSize/2];

				for(int chunkIdx = 0; chunkIdx < totalChunks; ++chunkIdx)
				{
					int chunkStartIndex = chunkIdx * fftSize;

					// the chunk size will always be fftSize, except for the 
					// last chunk, which will be however many samples are left in source
					int chunkSize = Math.min( leftChannel.length - chunkStartIndex, fftSize );

					// copy first chunk into our analysis array
					System.arraycopy( leftChannel, // source of the copy
							chunkStartIndex, // index to start in the source
							fftSamples, // destination of the copy
							0, // index to copy to
							chunkSize // how many samples to copy
							);

					// if the chunk was smaller than the fftSize, we need to pad the analysis buffer with zeroes        
					if ( chunkSize < fftSize )
					{
						// we use a system call for this
						Arrays.fill( fftSamples, chunkSize, fftSamples.length - 1, 0.0F );
					}

					// now analyze this buffer
					fft.forward( fftSamples );

					// and copy the resulting spectrum into our spectra array
					for(int i = 0; i < (fftSize / 2); ++i)
					{
						spectra[chunkIdx][i] = fft.getBand(i);
					}
				}

				jingle.close(); 

				double max = -1;
				double min = 99;
				for(int i = 0; i < spectra.length; i++) {
					double sum = 0;
					for(int j = 0; j < spectra[i].length; j++) {
						// System.out.print(spectra[i][j] + " ");
						sum += spectra[i][j];
					}
					double ele = (sum / spectra[i].length);
					outputFreqArr.add( ele );
					if(max < ele) {
						max = ele;
					} else if (min > ele){
						min = ele;
					}
				}

				System.out.println("max: " + max);
				System.out.println("min: " + min);
				outputMap.put("max", max);
				outputMap.put("min", min);
				outputMap.put("totalChunks;", totalChunks);
				outputMap.put("outputFreqArr", outputFreqArr);
			
				return outputFreqArr;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public double tempPowerOfTwo(double numThatNotPowerOfTwo){
			double[] numList = new double[36];
			for(int power = 1; power <= 36; power++) {
				numList[power - 1] = (long) Math.pow(2, power);
			}
			
			double nearNum = 0;
			for(int i = 0; i < numList.length; i++) {
				if (numThatNotPowerOfTwo >= numList[35]) {
					nearNum = numList[35];
					break;
				} else if(numList[i] < numThatNotPowerOfTwo) {
					continue;
				} else if (numList[i] == numThatNotPowerOfTwo) {
					nearNum = numList[i];
					break;
				} else {
					nearNum = numList[i - 1];
					break;
				}
			}
			
			return nearNum;
		}


	

}