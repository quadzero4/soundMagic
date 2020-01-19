package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TheVoiceMainCnt implements Initializable {
	@FXML
	private Button startBtn, exitBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		startBtn.setOnAction(event-> {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("../view/Stage1.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage)startBtn.getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.show();
			}	catch(IOException e) {
				e.printStackTrace();
			}
		});
		
		exitBtn.setOnAction(event -> { 
			//System.out.println("exit by exitBtn");
			System.exit(0);
		});
		
	}
	
}
