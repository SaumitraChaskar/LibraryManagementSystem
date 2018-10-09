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

public class AddUserController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private Button add;

    @FXML
    private Button sendmail;

    @FXML
    void AddButtonClicked(ActionEvent event) throws SQLException {
    	
    	
    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
    	Connection connect=csc.CreateConnection();
    	Statement s=connect.createStatement();
    	ResultSet rs=s.executeQuery("select count(*) from `test_db`.`booklist`;");
    	int booknew=0;
		while(rs.next())
		booknew=rs.getInt(1);
    	PreparedStatement addUser=connect.prepareStatement("insert into `test_db`.`visitors`(email_id,Return1At,Return2At,Return3At,book1,book2,book3) values(?,?,?,?,?,?,?);");
    	addUser.setString(1,email.getText());
    	addUser.setDate(2,null);
    	addUser.setDate(3,null);
    	addUser.setDate(4,null);
    	addUser.setString(5,"-1");
    	addUser.setString(6,"-2");
    	addUser.setString(7,"-3");
    	
    	addUser.executeUpdate();
    	System.out.println(name.getText()+" is now added");
    	System.out.println("Please add "+name.getText()+" to the database");
   
    }

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
    void SendMailClicked(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}

