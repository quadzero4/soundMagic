package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TheVoiceMain implements Initializable {
	@FXML private ImageView startbtn;
	@FXML private ImageView exitbtn;
	Stage primaryStage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		startbtn.setOnMouseClicked(event -> {
			try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("../application/stage1.fxml"));
			Scene scene = new Scene(root);
			primaryStage = (Stage)startbtn.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		exitbtn.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
	}

}
