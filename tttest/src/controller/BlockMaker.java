package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import onetwo.Voiceget;
import onetwo.pleasetell;

public class BlockMaker implements Initializable {
	
	private Image blockImg;
	private ImageView blockV;
	@FXML
	private StackPane stagePane;
	//@FXML private Pane blockPane;
//	@FXML private ImageView bg;
	@FXML private Canvas blockC;
	Stage primaryStage;
	Scene scene;
	GraphicsContext gc;
	Voiceget javaSoundRecorder;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//scene = new Scene(stagePane);
		System.out.println("3progress on");
		
		blockC.setOnMouseClicked(event -> {
				// TODO Auto-generated method stub 
				System.out.println("progress on");
				javaSoundRecorder  = new Voiceget();
				
				
		        Thread thread = new Thread(javaSoundRecorder);
		        thread.start();
		        System.out.println("start");
		        long startTime = System.currentTimeMillis();
				 long estimatedTime = System.currentTimeMillis() - startTime;
				while (estimatedTime < 3000) {
						  estimatedTime = System.currentTimeMillis() - startTime;
				}
				javaSoundRecorder.finish();
				System.out.println("finish");
				pleasetell tel = new pleasetell();
				List<Double> frefre = tel.freqVisualizer();
				System.out.println("freq ready");
			//	blockImg = new Image("file:../img/block.jpg");
			//	blockV = new ImageView(blockImg);
			//	blockV.setFitHeight(frefre.get(10)*10);
				//System.out.println("5progress on");
				//blockV.relocate(0, 0);
				gc = blockC.getGraphicsContext2D();
			//	gc.strokeText("This is a stroked Text", 10,frefre.get(100)*100);
				System.out.println(frefre.size());
				for(int i = 0; i<frefre.size(); i++) {
					gc.fillRect(0+10*i, 700-(frefre.get(i)*70+230), 10, frefre.get(i)*70+230);
				}
				Image image = new Image("file:../img/block.jpg");
		        gc.drawImage(image, 0, 0, 100, 100);
			//	blockPane.getChildren().add(blockV);
			//	scene = new Scene(bb);
			//	primaryStage.setScene(scene);
			//	primaryStage.show();
				System.out.println("end");
			});
		
	}	
}
