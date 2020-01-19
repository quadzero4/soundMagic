package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import onetwo.pleasetell;

public class BlockMaker implements Initializable {
	
	private Image blockImg;
	private ImageView blockV;
	@FXML
	private Pane stagePane;
	Stage primaryStage;
	Scene scene;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//scene = new Scene(stagePane);
		stagePane.setOnKeyPressed(event -> {
				// TODO Auto-generated method stub 
				pleasetell tel = new pleasetell();
				List<Double> frefre = tel.freqVisualizer();
				System.out.println("progress on");
				blockImg = new Image("../../img/block.jpg");
				blockV = new ImageView(blockImg);
				blockV.setFitHeight(frefre.get(10)*10);
				//blockV.relocate(0, 0);
				
				stagePane.getChildren().add(blockV);
				scene = new Scene(stagePane);
				primaryStage.setScene(scene);
				primaryStage.show();
			});
		
	}	
	
}
