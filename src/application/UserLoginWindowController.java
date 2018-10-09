package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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


public class UserLoginWindowController {

	    @FXML
	    private Button back;

	    @FXML
	    public TextField visitorid;

	    @FXML
	    public TextField visitorpassword;

	    @FXML
	    private Button login;
	    
	    @FXML
	    private Label wrong;
	    

	    @FXML
	    private Button reset;
	    
	    ArrayList<String>VisitorList = new ArrayList<String>();
	    ArrayList<String>VisitorPassword = new ArrayList<String>();

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
	    void LoginButtonClicked(ActionEvent event) throws IOException {
	    	
	    	
		    ConnectionSsetterClass csc = new ConnectionSsetterClass();
		    

			String adid=visitorid.getText();
	    	String adpass=visitorpassword.getText();
	    	wrong.setVisible(false);
	    	
		    try {
				Connection connect=csc.CreateConnection();
				Statement s=connect.createStatement();
				ResultSet rs= s.executeQuery("select * from `test_db`.`visitors`;");
				
				while(rs.next()) {
					String ids=rs.getString("id_visitors");
					String pass=rs.getString("password");
					
					if(adid.equals(ids) && adpass.equals(pass)) {
						
			    		//ChangeScenetoIssueBook();
						
						
			        	FXMLLoader floader=new FXMLLoader(getClass().getResource("UserWindow.fxml"));
			        	Parent root1 = (Parent) floader.load();
			        	
			        	UserWindowController uwc = floader.getController();
						uwc.SetText(visitorid.getText());
			        	
			        	Scene root1scene = new Scene(root1);
			        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			        	window.setTitle("User Window");
			        	window.setScene(root1scene);
			        	window.show();
					}
					else {
						wrong.setVisible(true);
					
					}
					
					
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		    
	    		
	    @FXML
	    void ResetButtonClicked(ActionEvent event) {
	    	visitorid.setText("");
	    	visitorpassword.setText("");
	    }
	    
		public void initialize(URL url, ResourceBundle rb) {
			/*Scene newParentScene = new Scene(newParent);
			IssueBookController controller = loader.getController();
			controller.setData(visitorid.getText());*/
			
		    }

		
		/*public void ChangeScenetoIssueBook() throws IOException {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserLogin.fxml"));
			Parent newParent= loader.load();
			
			Scene newParentScene = new Scene(newParent);
			IssueBookController controller = loader.getController();
			controller.InitData(visitorid.getText());
			
    	
		}*/
		
		

	}
