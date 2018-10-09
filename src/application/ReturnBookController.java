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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReturnBookController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableView<Books> mytable;

    @FXML
    private TableColumn<Books, String> title;


    @FXML
    private TableColumn<Books,Date> bookdate;

    @FXML
    private Button r1;

    @FXML
    private Button r2;

    @FXML
    private Button r3;
    
    public static String visitorid=null;
    
    String SetText(String Id) {
    	System.out.println("Hey");
    	visitorid=Id;
    	return Id;
    }

    @FXML
    void GoBack(ActionEvent event) throws IOException {

    	FXMLLoader floader=new FXMLLoader(getClass().getResource("UserWindow.fxml"));
    	Parent root1 = (Parent) floader.load();
    	Scene root1scene = new Scene(root1);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setTitle("Return Window");
    	window.setScene(root1scene);
    	window.show();
    }

    @FXML
    void Return1(ActionEvent event) throws SQLException {
    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
    	Connection conn=csc.CreateConnection();
    	
    	
    	String bookname=null;
    	Date bookdate=null;
    	Statement s = conn.createStatement();
    	ResultSet rs = s.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+visitorid+"';");
    	
    	while(rs.next()) {
    		bookname=rs.getString("book1");
    		bookdate=rs.getDate("Return1At");
    	}
    	
    	PreparedStatement ps1 = conn.prepareStatement("Update `test_db`.`visitors` set book1=-1, Return1At=null where id_visitors ='"+visitorid+"';");
    	ps1.executeUpdate();
    	
    	int stock=0;
    	Statement s1 = conn.createStatement();
    	ResultSet rs1 =s1.executeQuery("select * from `test_db`.`booklist` where title='"+bookname+"';");
    	while(rs1.next()) {
    		stock=rs1.getInt("stock_quantity");
    		stock=stock+1;
    	}
    	PreparedStatement ps = conn.prepareStatement("Update `test_db`.`booklist` set stock_quantity='"+stock+"' where title='"+bookname+"';");
    	ps.executeUpdate();
    	
    }

    @FXML
    void Return2(ActionEvent event) throws SQLException {
    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
    	Connection conn=csc.CreateConnection();
    	
    	
    	String bookname=null;
    	Statement s = conn.createStatement();
    	ResultSet rs = s.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+visitorid+"';");
    	
    	while(rs.next()) {
    		bookname=rs.getString("book2");
    	}
    	
    	PreparedStatement ps1 = conn.prepareStatement("Update `test_db`.`visitors` set book2=-2, Return2At=null where id_visitors ='"+visitorid+"';");
    	ps1.executeUpdate();
    	
    	int stock=0;
    	Statement s1 = conn.createStatement();
    	ResultSet rs1 =s1.executeQuery("select * from `test_db`.`booklist` where title='"+bookname+"';");
    	while(rs1.next()) {
    		stock=rs1.getInt("stock_quantity");
    		stock=stock+1;
    	}
    	PreparedStatement ps = conn.prepareStatement("Update `test_db`.`booklist` set stock_quantity='"+stock+"' where title='"+bookname+"';");
    	ps.executeUpdate();
    	
    

    }

    @FXML
    void Return3(ActionEvent event) throws SQLException {
    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
    	Connection conn=csc.CreateConnection();
    	
    	
    	String bookname=null;
    	Statement s = conn.createStatement();
    	ResultSet rs = s.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+visitorid+"';");
    	
    	while(rs.next()) {
    		bookname=rs.getString("book3");
    	}
    	
    	PreparedStatement ps1 = conn.prepareStatement("Update `test_db`.`visitors` set book3=-3, Return3At=null where id_visitors ='"+visitorid+"';");
    	ps1.executeUpdate();
    	int stock=0;
    	Statement s1 = conn.createStatement();
    	ResultSet rs1 =s1.executeQuery("select * from `test_db`.`booklist` where title='"+bookname+"';");
    	while(rs1.next()) {
    		stock=rs1.getInt("stock_quantity");
    		stock=stock+1;
    	}
    	PreparedStatement ps = conn.prepareStatement("Update `test_db`.`booklist` set stock_quantity='"+stock+"' where title='"+bookname+"';");
    	ps.executeUpdate();
    	
    
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		title.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		bookdate.setCellValueFactory(new PropertyValueFactory<Books,Date>("date"));
		
		mytable.setItems(getBooks());
	}
	
		
		public ObservableList<Books> getBooks(){
			
			
			ObservableList<Books> allBooks =FXCollections.observableArrayList();
			
		    ConnectionSsetterClass csc = new ConnectionSsetterClass();
		    
		    try {
				Connection connect=csc.CreateConnection();
				Statement s=connect.createStatement();
				ResultSet rs= s.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+ReturnBookController.visitorid+"';");
				
				while(rs.next()) {
					String title1=rs.getString("Book1");
					if(title1.equals("-1"))
						title1="-------";
					Date date1=rs.getDate("Return1At");
					Books b1=new Books(title1,date1);
					allBooks.add(b1);
					
					
					String title2=rs.getString("Book2");
					if(title2.equals("-2"))
						title2="-------";
					Date date2=rs.getDate("Return2At");
					Books b2=new Books(title2,date2);
					allBooks.add(b2);
					
					
					String title3=rs.getString("Book3");
					if(title3.equals("-3"))
						title3="-------";
					Date date3=rs.getDate("Return3At");
					Books b3=new Books(title3,date3);
					allBooks.add(b3);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    return allBooks;
		}
		
	}



