package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class BookListController implements Initializable {

    @FXML
    private ListView<String> listofbooks;

    @FXML
    private Button select;

    @FXML
    private TextArea pushin;
    
    public void InsertIntoText() {
    	
    	String textareastring = listofbooks.getSelectionModel().getSelectedItem();
    	this.pushin.setText(textareastring);
    }
    
    
	public void initialize(URL url, ResourceBundle rb) {
    	
    ConnectionSsetterClass csc = new ConnectionSsetterClass();
    
    try {
		Connection connect=csc.CreateConnection();
		Statement s=connect.createStatement();
		ResultSet rs= s.executeQuery("select * from `test_db`.`booklist`;");
		
		while(rs.next()) {
			String title=rs.getString("title");
			listofbooks.getItems().add(title);
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

}