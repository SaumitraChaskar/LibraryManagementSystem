package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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




public class RemoveBookController implements Initializable{
	
    @FXML
    private TableView<Books> booktable;

    @FXML
    private TableColumn<Books, Integer> bookid;

    @FXML
    private TableColumn<Books, String> title;

    @FXML
    private TableColumn<Books,String> author;

    @FXML
    private Button back;

    @FXML
    private Button remove;

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
    void RemoveButtonClicked(ActionEvent event) throws SQLException {
    	Books b =booktable.getSelectionModel().getSelectedItem();
    	String deletename=b.getTitle();
    	
    	ConnectionSsetterClass csc = new ConnectionSsetterClass();
		Connection connect=csc.CreateConnection();
		Statement s=connect.createStatement();
		s.executeUpdate("delete  from `test_db`.`booklist` where title='"+deletename+"';");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		title.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		author.setCellValueFactory(new PropertyValueFactory<Books,String>("name"));
		
		
		booktable.setItems(getBooks());
	}
	
		
		public ObservableList<Books> getBooks(){
			
			
			ObservableList<Books> allBooks =FXCollections.observableArrayList();
			
		    ConnectionSsetterClass csc = new ConnectionSsetterClass();
		    
		    try {
				Connection connect=csc.CreateConnection();
				Statement s=connect.createStatement();
				ResultSet rs= s.executeQuery("select * from `test_db`.`booklist`;");
				
				while(rs.next()) {
					String title=rs.getString("title");
					String author=rs.getString("author_fname")+" "+rs.getString("author_lname");
					int yor=rs.getInt("released_year");
					int pages=rs.getInt("pages");
					int stockq=rs.getInt("stock_quantity");
					
					allBooks.add(new Books(title,author, yor, stockq, pages));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return allBooks;
		
	}
}
