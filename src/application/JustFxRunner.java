package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JustFxRunner extends Application {
	
	Stage myStage;
	
	@Override
	public void start(Stage stage) throws Exception,SQLException{
		
		FineCalculator fc = new FineCalculator();
    	fc.CalculateFine();
    	
		Parent root =FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
		stage.setTitle("Welcome to Library");
		this.myStage=stage;
		stage.setScene(new Scene(root,600,500));
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
