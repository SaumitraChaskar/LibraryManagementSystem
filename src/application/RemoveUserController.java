package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class RemoveUserController implements  Initializable {




	    @FXML
	    private Button back;

	    @FXML
	    private TableView<Visitor> visitortable;

	    @FXML
	    private TableColumn<Visitor, Integer> visitorid;

	    @FXML
	    private TableColumn<Visitor,String> book1;

	    @FXML
	    private TableColumn<Visitor,String> book2;

	    @FXML
	    private TableColumn<Visitor,String> book3;

	    @FXML
	    private TableColumn<Visitor, Integer> fine;

	    @FXML
	    private Button remove;

	    @FXML
	    private Button clearrecord;

	    @FXML
	    void ClearTheRecord(ActionEvent event) throws SQLException {
	     	Visitor v = visitortable.getSelectionModel().getSelectedItem();
	    	int updateid=v.getId();
	    	
	    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
			Connection connect=csc.CreateConnection();
			PreparedStatement  clear = connect.prepareStatement("update `test_db`.`visitors`set book1=?,book2=?,book3=?,Return1At=?,Return2At=?,Return3At=? where id_visitors='"+updateid+"';");
			clear.setString(1,"-1");
			clear.setString(2,"-2");
			clear.setString(3,"-3");
			clear.setDate(4,null);
			clear.setDate(5,null);
			clear.setDate(6,null);
			clear.executeUpdate();
			//UPDATE `members` SET `contact_number` = '0759 253 542' WHERE `membership_number` = 1;
			
			/*PreparedStatement sfine=conn.prepareStatement("Update `test_db`.'visitors' set Fine=Fine+? where visitors_id='"+id+"';");
			sfine.setInt(1,datediff*20);
			sfine.executeUpdate();*/
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
	    void RemoveTheUser(ActionEvent event) throws SQLException {
	    	Visitor v = visitortable.getSelectionModel().getSelectedItem();
	    	int deleteid=v.getId();
	    	
	    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
			Connection connect=csc.CreateConnection();
			Statement s=connect.createStatement();
			int RowsAffected=s.executeUpdate("delete  from `test_db`.`visitors` where id_visitors='"+deleteid+"';");
	    	
	    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		visitorid.setCellValueFactory(new PropertyValueFactory<Visitor,Integer>("id"));
		book1.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book1"));
		book2.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book2"));
		book3.setCellValueFactory(new PropertyValueFactory<Visitor,String>("book3"));
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
				
				if(book1.equals("-1") && book2.equals("-2") && book3.equals("-3")) {
					book1return=null;
					book2return=null;
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
