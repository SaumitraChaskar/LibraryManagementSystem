package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class AdminWindowController {
		
		
		
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label adminlabel;

	    @FXML
	    private Label passwordlabel;

	    @FXML
	    private TextField adminid;

	    @FXML
	    private TextField adminpassword;

	    @FXML
	    private Button login;

	    @FXML
	    private Button reset;
	    
	    @FXML
	    void LoginButtonClicked(ActionEvent event) throws IOException {
	    	//System.out.println(s);
	    	String adid=adminid.getText();
	    	String adpass=adminpassword.getText();
	    	if(adid.equals("admin") && adpass.equals("admin123")) {
	         	FXMLLoader floader=new FXMLLoader(getClass().getResource("AdminWindow.fxml"));
	        	Parent root1 = (Parent) floader.load();
	        	Scene root1scene = new Scene(root1);
	        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        	window.setTitle("Admin Window");
	        	window.setScene(root1scene);
	        	window.show();
	    	}
	    	else {
	    		System.out.println("Wrong Admin ID or Password");
	    	}
	    	
	    }

	    @FXML
	    void ResetButtonClicked(ActionEvent event) {
	    	adminid.setText("");
	    	adminpassword.setText("");
	    	
	    }
	    
	    @FXML
	    void GoBack(ActionEvent event) throws IOException {
        	FXMLLoader floader=new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        	Parent root1 = (Parent) floader.load();
        	Scene root1scene = new Scene(root1);
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setTitle("Admin Window");
        	window.setScene(root1scene);
        	window.show();
	    }

	    @FXML
	   public void initialize() {
	    }
	    
	    
	    
}
