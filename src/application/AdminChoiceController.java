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
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class AdminChoiceController implements Initializable{
	
	


		@FXML
		private Button back;
		
	    @FXML
	    private Button addV;

	    @FXML
	    private Button removeV;

	    @FXML
	    private Button addB;

	    @FXML
	    private Button removeB;

	    @FXML
	    private Button mailV;
	    
	    
	    @FXML
	    void GoBack(ActionEvent event) throws IOException {
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Visitor List");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    }

	    @FXML
	    void addVisitorClicked(ActionEvent event) throws IOException {
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("VisitorList.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Visitor List");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    }
	    
	    @FXML
	    void removeVistorClicked(ActionEvent event) throws IOException {
	     	FXMLLoader floader=new FXMLLoader(getClass().getResource("RemoveUser.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Visitor List");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    }
	    
	    @FXML
	    void addBookClicked(ActionEvent event) throws IOException {
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("AddBook.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Book Add");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    }
	    @FXML
	    void removeBookClicked(ActionEvent event) throws IOException{
	    	System.out.println("Yup");
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("RemoveBook.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Remove Book");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
