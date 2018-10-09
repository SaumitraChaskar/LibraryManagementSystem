package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class JustListController implements Initializable {
	
	@FXML
	private ListView<String> lof;
	
	@FXML
	private ListView<String> loa;

    @FXML
    private Tab books;

    @FXML
    private Tab author;
    
    @FXML
    private Button back;

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


    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	  
    	
        ConnectionSsetterClass csc = new ConnectionSsetterClass();
        
        try {
    		Connection connect=csc.CreateConnection();
    		Statement s=connect.createStatement();
    		ResultSet rs= s.executeQuery("select * from `test_db`.`booklist`;");
    		while(rs.next()) {
    			String title=rs.getString("title");
    			lof.getItems().add(title);
    			
    		}
    		Statement s1= connect.createStatement();
    		ResultSet rs1= s1.executeQuery("select distinct author_fname,author_lname from `test_db`.`booklist`;");
    		while(rs1.next()) {
    			String authorf=rs1.getString("author_fname");
    			String authorl=rs1.getString("author_lname");
    			loa.getItems().add(authorf+" "+authorl);
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        }


}
