package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import onetwo.Voiceget;
import onetwo.pleasetell;

public class Keycontroller implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 37) {
			Canvas canvas = new Canvas(1280, 800);
			GraphicsContext gc;
			/*
			Voiceget javaSoundRecorder  = new Voiceget();
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
			pleasetell tel = new pleasetell();
			List<Double> frefre = tel.freqVisualizer();
			
			gc = canvas.getGraphicsContext2D();
			Image image = new Image("file:block.jpg");
			gc.drawImage(image, 100, 100, 200, frefre.get(3)*100);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
