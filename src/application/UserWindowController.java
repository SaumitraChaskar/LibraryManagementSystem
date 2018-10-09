package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserWindowController implements Initializable {
		
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button issue;

    @FXML
    private Button bookreturn;

    @FXML
    private Button payfine;
    
    @FXML
    private Label visitorid;

    
    @FXML
    void GoBack(ActionEvent event) throws IOException {
    	FXMLLoader floader=new FXMLLoader(getClass().getResource("UserLogin.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Admin Window");
    	window.setScene(root1scene);
    	window.show();
    }
    @FXML
    void IssueBook(ActionEvent event) throws IOException {
     	FXMLLoader loader=new FXMLLoader(getClass().getResource("IssueBook.fxml"));
    	Parent root1 = (Parent) loader.load();
    	
    	IssueBookController ibc = loader.getController();
    	System.out.println(visitorid.getText());
    	ibc.SetText(visitorid.getText());
    	
    	
    	
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("User Login");
    	window.setScene(root1scene);
    	window.show();
    	
    }
    
    void SetText(String Id) {
    	visitorid.setText(Id);
    }

    @FXML
    void PayFine(ActionEvent event) throws IOException {
     	FXMLLoader floader=new FXMLLoader(getClass().getResource("FineWindow.fxml"));
    	Parent root1 = (Parent) floader.load();
    	
    	FineWindowController ibc = floader.getController();
    	ibc.SetText(visitorid.getText());
    	
    	
    	
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Fine Window");
    	window.setScene(root1scene);
    	window.show();
    }

    @FXML
    void ReturnBook(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ReturnBook.fxml"));
    	Parent root = (Parent) loader.load();
    	
    	ReturnBookController rbc = loader.getController();
    	rbc.SetText(visitorid.getText());
    	
    	
    	FXMLLoader floader=new FXMLLoader(getClass().getResource("ReturnBook.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Return Window");
    	window.setScene(root1scene);
    	window.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
}
