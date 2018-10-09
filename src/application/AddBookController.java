package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController implements Initializable {
	

    	@FXML
    	private Button add;

    	@FXML
    	private Button reset;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField title;

	    @FXML
	    private TextField aFname;

	    @FXML
	    private TextField aLname;

	    @FXML
	    private TextField pages;

	    @FXML
	    private TextField stock;

	    @FXML
	    private TextField releaseyear;

	    @FXML
	    void GoBack(ActionEvent event) throws IOException {
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("AdminWindow.fxml"));
        	Parent root1 = (Parent) floader.load();
        	Scene root1scene = new Scene(root1);
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setTitle("Admin Window");
        	window.setScene(root1scene);
        	window.show();
	    }
	    
	    @FXML
	    void AddBook(ActionEvent event) throws SQLException {
	    	
	    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
	    	Connection connect=csc.CreateConnection();
	    	Statement s=connect.createStatement();
	    	ResultSet rs=s.executeQuery("select count(*) from `test_db`.`booklist`;");
	    	int booknew=0;
			while(rs.next())
			booknew=rs.getInt(1);
	    	PreparedStatement addBook=connect.prepareStatement("insert into `test_db`.`booklist`(title,author_fname,author_lname,released_year,stock_quantity,pages) values(?,?,?,?,?,?)");
	    	addBook.setString(1,title.getText());
	    	addBook.setString(2,aFname.getText());
	    	addBook.setString(3,aLname.getText());
	    	addBook.setInt(4,Integer.parseInt(releaseyear.getText()));
	    	addBook.setInt(5,Integer.parseInt(stock.getText()));
	    	addBook.setString(6,pages.getText());
	    	System.out.println("Happening");
	    	
	    	addBook.executeUpdate();
	    	
	    }

	    @FXML
	    void ResetVClicked(ActionEvent event) {
	    	title.setText("");
	    	aFname.setText("");
	    	aLname.setText("");
	    	pages.setText("");
	    	stock.setText("");
	    	releaseyear.setText("");
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
		}

}
