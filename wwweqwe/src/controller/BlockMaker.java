package controller;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import onetwo.Voiceget;
import onetwo.pleasetell;
import stttest.teststt;

public class BlockMaker implements Initializable {
	private Image blockImg;
	private ImageView blockV;
//	@FXML private StackPane stagePane;
//  @FXML private Pane blockPane;
//	@FXML private ImageView bg;
	@FXML private Canvas blockC;
	Stage primaryStage;
	Scene scene;
	GraphicsContext gc;
	Voiceget javaSoundRecorder;
	@FXML private ImageView user;
	@FXML private ImageView bg;
	@FXML private Rectangle bnt;
	@FXML private Group gr;
	@FXML Rectangle rec1, rec2, rec3, rec4, rec5, rec6, rec7;
	private Rectangle[] Rec_arr;
	private Task<Void> task;
	private boolean jump_check = false;
	List<Double> frefre;
	boolean blockFlag=false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		blockC.setFocusTraversable(true);
		blockC.setOnMouseClicked(event -> {
				// TODO Auto-generated method stub 
				javaSoundRecorder  = new Voiceget();
		        Thread thread = new Thread(javaSoundRecorder);
		        thread.start();
		        long startTime = System.currentTimeMillis();
				 long estimatedTime = System.currentTimeMillis() - startTime;
				while (estimatedTime < 5000) {
						  estimatedTime = System.currentTimeMillis() - startTime;
				}
				javaSoundRecorder.finish();
				
				pleasetell tel = new pleasetell();
				frefre = tel.freqVisualizer();
				blockFlag=true;
				gc = blockC.getGraphicsContext2D();
				System.out.println(frefre.size());
				for(int i = 0; i<frefre.size(); i++) {
					Random random = new Random();
					gc.setFill(new Color(random.nextDouble(), random.nextDouble(),0.5, 1));
					gc.fillRect(280+10*i, 1000-(frefre.get(i)*300+522), 10, frefre.get(i)*300+522);
				}
			});
		blockC.setOnKeyPressed(event -> {
			try {
				KeyCode keyCode = event.getCode();
				System.out.println(keyCode);
				if(KeyCode.UP.equals(keyCode) || KeyCode.LEFT.equals(keyCode) || KeyCode.RIGHT.equals(keyCode) || KeyCode.DOWN.equals(keyCode))
					user_move(event);
				else if(KeyCode.T.equals(keyCode))
					turn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		initializeGroupArray();
		gravity();
	}
	
	public void initializeGroupArray() {
		Rec_arr = new Rectangle[7];
		Rec_arr[0] = rec1;
		Rec_arr[1] = rec2;
		Rec_arr[2] = rec3;
		Rec_arr[3] = rec4;
		Rec_arr[4] = rec5;
		Rec_arr[5] = rec6;
		Rec_arr[6] = rec7;
	}
	public boolean getjump_check() {
		return jump_check;
	}
	public void setjump_check(boolean jump_check) {
		this.jump_check = jump_check;
	}
	
	public void user_move(KeyEvent event) throws Exception{
		KeyCode keyCode = event.getCode();
		System.out.println(keyCode);
		//check_label.setText(user.getLayoutY() + "" + jump_check);
		if (KeyCode.UP.equals(keyCode)) {
			if(!bottom_check()) jump_check = true;
		} else if (KeyCode.LEFT.equals(keyCode) && behind_check()) {
			user.setLayoutX(user.getLayoutX() - 10);
		//	user.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		} else if (KeyCode.RIGHT.equals(keyCode) && front_check()) {
			user.setLayoutX(user.getLayoutX() + 10);
		//	user.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		}
		if(user.getLayoutX() > 899 && user.getLayoutY() < 210 && ((gr.getRotate()/90)%4==2) ) {
			exit();
		}
		
	}
	
	public void gravity() {
		task = new Task<Void>() {

			@Override
			public Void call() throws Exception {
				for (;;) {
					if (getjump_check()) {
						
						for (int i = -13, user_height =  (int)user.getLayoutY(); i <= 14; i++) {
							user.setLayoutY(user_height - (-(i * i) + 196));
							Thread.sleep(40);
							if(!bottom_check()) break;
							//System.out.println(1);
						}
						setjump_check(false);
					}
					if (bottom_check()) {
						user.setLayoutY(user.getLayoutY() + 9);}
					Thread.sleep(30);
					
				}
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	public boolean bottom_check() {
	      boolean check = true;// false∏È æ»∂≥æÓ¡¸(¿Ã∞« ≈∞∫∏µÂ ¿‘∑¬ ¬ ø°)//true∏È ∂≥æÓ¡¸
	      double[] img_side = { 
	            user.getLayoutX(), 
	            user.getLayoutX() + user.getLayoutBounds().getMaxX(),
	            user.getLayoutY() + user.getLayoutBounds().getMaxY() };
	      if (user.getLayoutY() > 850)
	         return false; // æ∆∑°∑Œ ¥ı¿ÃªÛ æ»∂≥æÓ¡ˆµµ∑œ
	      int j=0;
	      if((((gr.getRotate()/90)%4!=2) && ((gr.getRotate()/90)%4 != -2))) {
	    	  j=6;
	      }
	      if(blockFlag) { //280
	    	  for (int i=0; i < frefre.size(); i++) {
	 	         double[] Rec_side = { 
	 	               280 + i*10, // √÷º“ X¡¬«•
	 	              280+10*(i+1), // √÷¥Î X¡¬«•
	 	             1000-(frefre.get(i)*300+522),
	 	               1000};
	 	         if(Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
	 	            if(Rec_side[2] - 10 < img_side[2] && img_side[2] < Rec_side[2] + 10) {
	 	            	user.setLayoutY(Rec_side[2] - user.getLayoutBounds().getMaxY());
	 	            	check = false;
	 					return check;
	 	            }
	 	      }
	      }
	      for (; j < Rec_arr.length; j++) {
	         double[] Rec_side = { 
	               Rec_arr[j].getLayoutX(), // √÷º“ X¡¬«•
	               Rec_arr[j].getLayoutBounds().getMaxX() + Rec_arr[j].getLayoutX(), // √÷¥Î X¡¬«•
	               Rec_arr[j].getLayoutY(),
	               Rec_arr[j].getLayoutBounds().getMaxY() + Rec_arr[j].getLayoutY()};
	         if(Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
	            if(Rec_side[2] - 10 < img_side[2] && img_side[2] < Rec_side[2] + 10) {
	            	user.setLayoutY(Rec_side[2] - user.getLayoutBounds().getMaxY());
	            	check = false;
					return check;
	            }
	      }
	      return check;
	   }

	public boolean front_check() {
		boolean check = true;// false∏È æ»∂≥æÓ¡¸(¿Ã∞« ≈∞∫∏µÂ ¿‘∑¬ ¬ ø°)//true∏È ∂≥æÓ¡¸

		double[] img_side = {
				user.getLayoutY(),
				user.getLayoutY() + user.getLayoutBounds().getMaxY(),
				user.getLayoutX() + user.getLayoutBounds().getMaxX()};
		 int j=0;
	      if((((gr.getRotate()/90)%4!=2) && ((gr.getRotate()/90)%4 != -2))) {
	    	  j=6;
	      }
	      if(blockFlag) { //280
	    	  for (int i=0; i < frefre.size(); i++) {
	 	         double[] Rec_side = { 
	 	               280 + i*10, // √÷º“ X¡¬«•
	 	              280+10*(i+1), // √÷¥Î X¡¬«•
	 	             1000-(frefre.get(i)*300+522),
	 	               1000};
	 	        if (Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
					if (Rec_side[2] <= img_side[2] && img_side[2] < Rec_side[2] + 20) {
						user.setLayoutX(Rec_side[2] - user.getLayoutBounds().getMaxX());
						check = false;
						return check;
					}
	    	  	}
	      }
			for (; j < Rec_arr.length; j++) {
				double[] Rec_side = { 
						Rec_arr[j].getLayoutY(), 
						Rec_arr[j].getLayoutBounds().getMaxY() + Rec_arr[j].getLayoutY(),
						Rec_arr[j].getLayoutX(),
						Rec_arr[j].getLayoutBounds().getMaxX() + Rec_arr[j].getLayoutX()};
				if (Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
					if (Rec_side[2] <= img_side[2] && img_side[2] < Rec_side[2] + 20) {
						
						user.setLayoutX(Rec_side[2] - user.getLayoutBounds().getMaxX());
						check = false;
						return check;
					}

			}
		return check;
	}

	public boolean behind_check() {
		boolean check = true;// false∏È æ»∂≥æÓ¡¸(¿Ã∞« ≈∞∫∏µÂ ¿‘∑¬ ¬ ø°)//true∏È ∂≥æÓ¡¸

		double[] img_side = {
				user.getLayoutY(),
				user.getLayoutY() + user.getLayoutBounds().getMaxY(),
				user.getLayoutX()};
		 int j=0;
	      if((((gr.getRotate()/90)%4!=2) && ((gr.getRotate()/90)%4 != -2))) {
	    	  j=6;
	      }
	      if(blockFlag) { //280
	    	  for (int i=0; i < frefre.size(); i++) {
	 	         double[] Rec_side = { 
	 	               280 + i*10, // √÷º“ X¡¬«•
	 	              280+10*(i+1), // √÷¥Î X¡¬«•
	 	             1000-(frefre.get(i)*300+522),
	 	               1000};
	 	        if (Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
					if (Rec_side[2] - 20 < img_side[2] && img_side[2] <= Rec_side[2]) {
						user.setLayoutX(Rec_side[2]);
						check = false;
						return check;
					}
	    	  	}
	      }
			for (; j < Rec_arr.length; j++) {
				double[] Rec_side = { 
						Rec_arr[j].getLayoutY(), 
						Rec_arr[j].getLayoutBounds().getMaxY() + Rec_arr[j].getLayoutY(),
						Rec_arr[j].getLayoutBounds().getMaxX() + Rec_arr[j].getLayoutX()};
				if (Rec_side[0] < img_side[1] && img_side[0] < Rec_side[1])
					if (Rec_side[2] - 20 < img_side[2] && img_side[2] <= Rec_side[2]) {
						
						user.setLayoutX(Rec_side[2]);
						check = false;
						return check;
					}

			}
		return check;
	}
	
	public void turn() {
		try {
			gr.setRotate(gr.getRotate() + 90);
		
			/*
			teststt.streamingMicRecognize();
			if(teststt.turn == 1) {
				gr.setRotate(gr.getRotate() + 90);
				if((gr.getRotate()/90)%4==2 || (gr.getRotate()/90)%4== -2){
					bnt.setFill(Color.GREEN);
				}else {
					bnt.setFill(Color.RED);
				}
				//bg.setRotate(bg.getRotate() + 90);
				//redBtn.setRotate(redBtn.getRotate() + 90);
				//System.out.printf("%d, %d\n", redBtn.getLayoutX(), redBtn.getLayoutY());
			}else if(teststt.turn == 2) {
				gr.setRotate(gr.getRotate() - 90);
				if((gr.getRotate()/90)%4==2 || (gr.getRotate()/90)%4== -2){
					bnt.setFill(Color.GREEN);
				}else {
					bnt.setFill(Color.RED);
				}
			}*/
			}catch(Exception e) {
				
			}
	}
	
	void exit() {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("../application/scoreBoard.fxml"));
			Scene scene = new Scene(root);
			primaryStage = (Stage)blockC.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}
