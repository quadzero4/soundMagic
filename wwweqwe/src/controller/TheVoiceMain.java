package controller;

import java.net.URL;
import java.util.ResourceBundle;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import onetwo.MinimImpl;

public class TheVoiceMain implements Initializable {
	@FXML private ImageView startbtn;
	@FXML private ImageView exitbtn;
	Stage primaryStage;
	AudioPlayer song;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Minim minim = MinimImpl.getMinimInstance();
		song = minim.loadFile("d:\\fahrenhaidt- home under the sky.mp3");
		song.loop();
		

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
