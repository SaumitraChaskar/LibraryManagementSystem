package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class IssueBookController implements Initializable {
	
	

	    @FXML
	    private Button back;

	    @FXML
	    private Button issue;
	    
	    @FXML 
	    private Button smallclose;
	    

	    @FXML
	    private Label idofvisitor;
	    
	    @FXML 
	    private VBox hidemsg;
	    

	    @FXML
	    private TableView<Books> booktable;

	    @FXML
	    private TableColumn<Books,String> titlecol;

	    @FXML
	    private TableColumn<Books, String> authorcol;

	    @FXML
	    private TableColumn<Books, Integer> pagescol;

	    @FXML
	    private TableColumn<Books,Integer> stockcol;

	    @FXML
	    private TableColumn<Books,Integer> yorcol;

	    
	    void SetText(String Id) {
	    	System.out.println("Idhar");
	    	System.out.println(idofvisitor);
	    	idofvisitor.setText(Id);
	    	
	    }
	    
	    @FXML
	    void SmallCloseClicked(ActionEvent event) {
	    	hidemsg.setVisible(false);
	    	
	    }


	    @FXML
	    void GoBack(ActionEvent event) throws IOException {
	    	
	     	FXMLLoader loader=new FXMLLoader(getClass().getResource("IssueBook.fxml"));
	    	Parent root = (Parent) loader.load();
	    	
	    	/*UserWindowController uwc = loader.getController();
	    	uwc.SetText(idofvisitor.getText());*/
	    	
	    	FXMLLoader floader=new FXMLLoader(getClass().getResource("UserWindow.fxml"));
        	Parent root1 = (Parent) floader.load();
        	Scene root1scene = new Scene(root1);
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setTitle("User Window");
        	window.setScene(root1scene);
        	window.show();
	    }
	    
	    public void getBook() {
	    	Books textareastring = booktable.getSelectionModel().getSelectedItem();
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
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	    } 
	    
	 @FXML
	 public void IssueTheBook(ActionEvent e) throws SQLException {
			
		 
		Date now = new Date();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
	
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE,0); // Adding 7 days
		String output = dateFormatter.format(c.getTime());
		 
		 
		 Books b =booktable.getSelectionModel().getSelectedItem();
		 
		 
		 ConnectionSsetterClass csc = new ConnectionSsetterClass();
		 Connection conn=csc.CreateConnection();
		 
		 
		 int idVisitor=Integer.parseInt(idofvisitor.getText());
		 
		 	int flag=0;
			int datediff=0;
			
			Statement s1 = conn.createStatement();
			Statement statement = conn.createStatement();
			
			Statement s4 = conn.createStatement();
			ResultSet r4 = null;
			
			Statement s5 = conn.createStatement();
			
			ResultSet book_check = statement.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+idVisitor+"';");
			try {
				int rowsaffected = s1.executeUpdate("UPDATE `test_db`.`booklist` SET stock_quantity='"+(b.stock-1)+"' WHERE title='"+b.getTitle()+"';");
				
				while(book_check.next()) {
					if(book_check.getString("book1").equals("-1") && b.stock>0) {
						flag=1;
						System.out.println(book_check.getString("book1"));
						System.out.println("Book is"+b.getTitle());
						System.out.println("1 True");
						System.out.println(book_check.getString("book1").equals("-1"));
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book1=? where id_visitors=?;");
						stmt.setString(1,b.getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,idVisitor); 
						stmt.executeUpdate();
						Statement s12 = conn.createStatement();
						ResultSet bc = s12.executeQuery("select * from `test_db`.`visitors`;");
						
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return1At='"+output+"' where id_visitors=?;");   
						
						stmtdate.setInt(1,Integer.parseInt(idofvisitor.getText()));
						stmtdate.executeUpdate();
						/*if(flag==1) {
							hidemsg.setVisible(true);
						}*/
						break;
					}
					else if(book_check.getString("book2").equals("-2") && b.stock>0) {
						flag=1;
						System.out.println("2 True");
						System.out.println(book_check.getString("book2").equals("-2"));
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book2=? where id_visitors=?;");  
						stmt.setString(1,b.getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,idVisitor); 
						stmt.executeUpdate();
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return2At='"+output+"'where id_visitors=?;");   
						
						stmtdate.setInt(1,idVisitor); 
						stmtdate.executeUpdate();
						

						break;
						}
					else if(book_check.getString("book3").equals("-3")  && b.stock>0) {
						flag=1;
						System.out.println(book_check.getString("book3").equals("-3"));
						System.out.println("3 True");
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book3=? where id_visitors=?;");  
						stmt.setString(1,b.getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,idVisitor); 
						stmt.executeUpdate();
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return3At='"+output+"' where id_visitors=?;");   
						 
						stmtdate.setInt(1,idVisitor);
						stmtdate.executeUpdate();

						break;
					}
					else {
						System.out.println("Cannot Get More Books");
					}
					
					}
			}
	
			 catch (SQLException e1) {
				System.out.println(e1+ " this is the error");
				e1.printStackTrace();
			}
	 }

	 
		 

	 @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		titlecol.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		yorcol.setCellValueFactory(new PropertyValueFactory<Books,Integer>("released_year"));
		authorcol.setCellValueFactory(new PropertyValueFactory<Books,String>("name"));
		stockcol.setCellValueFactory(new PropertyValueFactory<Books,Integer>("stock"));
		pagescol.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
		
		
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
	

