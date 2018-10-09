package application;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.LoaderHandler;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.event.ActionEvent;


public class FXMLController implements Initializable{

	@FXML
    private Button visitorbutton;

    @FXML
    private Button adminbutton;

    @FXML
    private Button booklist;

    @FXML
    void AdminButtonClicked(ActionEvent event) throws IOException {
    	
     	FXMLLoader floader=new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Admin Login Window");
    	window.setScene(root1scene);
    	window.show();
    	
    }

    @FXML
    void TakeALook(ActionEvent event) throws IOException {
    	
     	FXMLLoader floader=new FXMLLoader(getClass().getResource("TitlesAndAuthors.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Books And Authors");
    	window.setScene(root1scene);
    	window.show();
    	
    	
    }

    @FXML
    void VisitorButtonClicked(ActionEvent event) throws IOException {
    	
     	FXMLLoader floader=new FXMLLoader(getClass().getResource("UserLogin.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("User Login");
    	window.setScene(root1scene);
    	window.show();
    	
    	
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    	
    }

}
