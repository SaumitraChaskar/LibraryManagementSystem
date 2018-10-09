package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VisitorListController implements Initializable {


	    @FXML
	    private Button back;

	    @FXML
	    private Button add;

	    @FXML
	    private TableView<Visitor> visitortable;

	    @FXML
	    private TableColumn<Visitor,Integer> visitorid;

	    @FXML
	    private TableColumn<Visitor,String> book1;

	    @FXML
	    private TableColumn<Visitor,String> book2;

	    @FXML
	    private TableColumn<Visitor, String> book3;

	    @FXML
	    private TableColumn<Visitor, Date> book1trturn;

	    @FXML
	    private TableColumn<Visitor,Date> book2return;

	    @FXML
	    private TableColumn<Visitor, Date> book3return;

	    @FXML
	    private TableColumn<Visitor, Integer> fine;

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
	    void AddVisitor(ActionEvent event) throws IOException, SQLException {
	    	//System.out.println("hey");
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("AddUser.fxml"));
	    	Parent root1 = (Parent) floader.load();
	    	Scene root1scene = new Scene(root1);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setTitle("Add User Portal");
	    	window.setResizable(true);
	    	window.setScene(root1scene);
	    	window.show();
	    	System.out.println("Helo");
	    	
	    }


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		visitorid.setCellValueFactory(new PropertyValueFactory<Visitor,Integer>("id"));
		book1.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book1"));
		book2.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book2"));
		book3.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book3"));
		book1trturn.setCellValueFactory(new PropertyValueFactory<Visitor,Date>("return1"));
		book2return.setCellValueFactory(new PropertyValueFactory<Visitor,Date>("return2"));
		book3return.setCellValueFactory(new PropertyValueFactory<Visitor,Date>("return3"));
		fine.setCellValueFactory(new PropertyValueFactory<Visitor,Integer>("fine"));
		
		
		visitortable.setItems(getVisitors());
	}
	
		
		public ObservableList<Visitor> getVisitors(){
			

			    
			ObservableList<Visitor> allVisitors =FXCollections.observableArrayList();
			
		    ConnectionSsetterClass csc = new ConnectionSsetterClass();
		    
		    try {
				Connection connect=csc.CreateConnection();
				Statement s=connect.createStatement();
				ResultSet rs= s.executeQuery("select * from `test_db`.`visitors`;");
				
				while(rs.next()) {
					 /*SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DATE, 7); 
						String output = dateFormatter.format(c.getTime());*/
						
						long millis=System.currentTimeMillis();  
						java.sql.Date date=new java.sql.Date(millis);
						
						
					int id=rs.getInt("id_visitors");
					String book1=rs.getString("book1");
					String book2=rs.getString("book2");
					String book3=rs.getString("book3");
					Date book1return=rs.getDate("Return1At");
					Date book2return=rs.getDate("Return2At");
					Date book3return=rs.getDate("Return3At");
					int fine=rs.getInt("Fine");
					String password=rs.getString("password");
					
					if(book1.equals("-1")) {
						book1return=null;
					}
					if(book2.equals("-2")) {
						book2return=null;
					}
					if(book1.equals("-3")) {
						book3return=null;
					}
						
						
					allVisitors.add(new Visitor(id,book1,book2,book3,book1return,book2return,book3return,fine,password));
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    
		    return allVisitors;
		
	}

}
