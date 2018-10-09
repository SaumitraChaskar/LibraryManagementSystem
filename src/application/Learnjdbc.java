/*package application;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DateFormatter;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

// Use DATEDIFF
public class Learnjdbc extends Application {

	static int num_books;
	static Date now = new Date();
	public static void main(String[] args) throws SQLException, InterruptedException, FileNotFoundException {
		
		String url ="jdbc:mysql://localhost:3306/test_db?useSSL=false";  			// Establish connection
		Connection conn = DriverManager.getConnection(url,"root","dada9946");
		int id=0;
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("Todays Date:   " + dateFormatter.format(now));


		
		Books[] books=new Books[50]; 
		Scanner scan = new Scanner(System.in);
		Statement s0 = conn.createStatement();
		List<Visitor> visitor_list = new ArrayList<Visitor>();
		List<Integer> visitorids = new ArrayList<Integer>();
		Statement s5 = conn.createStatement();
		ResultSet r5= s5.executeQuery("select count(*) from `test_db`.`booklist`;");
		while(r5.next())
		num_books=r5.getInt(1);
		
		Statement visitorlist= conn.createStatement();
		ResultSet visitor=visitorlist.executeQuery("select * from `test_db`.`visitors`;");
		while(visitor.next()) {
			int toadd=visitor.getInt("id_visitors");
			visitorids.add(toadd);
		}
		
		for(int x:visitorids) {                // adding visitors
			Visitor v= new Visitor(x);
			visitor_list.add(v);
		}
		
		Administrator admin = new Administrator(1);   	// adding a administrator
		System.out.println("ENTER YOUR STATUS::\nVisitor or Administrator\n");
		String person=scan.next();
		
		
		if(person.equalsIgnoreCase("Visitor")){
		id=VisitorChoice(person,visitorids,conn,books,visitor_list);
		}	
		else if(person.equalsIgnoreCase("Administrator")) {
			AdminChoice(admin,conn,books);
		}
		DisplayBooks(conn, id);
	}
	
	//-----------------------------------------------------------------------------------------------------//
//Function for issuing the book
	
	static void Bookissuefunc(int id,Connection conn,Books[] books,List<Visitor> visitor_list) throws SQLException {
		 Scanner sc = new Scanner(System.in);
		 SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 7); // Adding 5 days
			String output = dateFormatter.format(c.getTime());
			System.out.println("Date after a week:    "+output);
		 int i=0;
	while(true) {
		System.out.println("\n HOW DO YOU WANT TO SEARCH THE BOOKS ??(IF NOT PRESS 0)");
		System.out.println("ENTER>> \n");
		System.out.println("1:: by TITLE");
		System.out.println("2:: by AUTHOR NAME");
		System.out.println("3:: by RELEASED YEAR");
		int search=sc.nextInt();
		
	System.out.println("\n IF YOU WANT SEARCH, ENTER 'y' (IF NOT PRESS n)");
	String choice = sc.next();
	if("y".equals(choice)) {
	switch (search) {
	case 1:
	for(i=0;i<num_books;i++) {
		System.out.println((i+1)+" "+books[i].getTitle());
	}
		break;

	case 2:	
	for(i=0;i<num_books;i++){
		System.out.println((i+1)+" "+books[i].getName()+" "+books[i].getTitle());
	}	
	break;
	case 3:
	for(i=0;i<num_books;i++)
	{
		System.out.println((i+1)+" "+books[i].getName()+" "+books[i].getReleased_year());
	}
	break;
	default:
		System.out.println("\n NOT A VALID REQUEST");
		break;
			}
		}
	else {
		break;
		}
	
	}
	System.out.println("Which book do you want to issue");
    String book_name = sc.nextLine();
    book_name=sc.nextLine();  // Consume newline left-over
    int selected=0;
	for(i=0;i<num_books;i++) {
		  if(books[i].getTitle().equalsIgnoreCase(book_name)) {
			selected=i;
			int l=books[i].getStock()-1;
			books[i].setStock(l);
			
			Statement s4 = conn.createStatement();
			ResultSet r4;
			
			Statement s5 = conn.createStatement();
			
			int datediff=0;
			Statement s1 = conn.createStatement();
			Statement statement = conn.createStatement();
			ResultSet book_check = statement.executeQuery("select * from `test_db`.`visitors`;");
			try {
				int rowsaffected = s1.executeUpdate("UPDATE `test_db`.`booklist` SET stock_quantity='"+l+"' WHERE title='"+book_name+"';");
				visitor_list.get(id).insertbook(books[selected]);
				while(book_check.next()) {
					if(book_check.getInt("id_visitors")==id) {
					if(book_check.getString("book1").equals("one")) {
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book1=? where id_visitors=?;");  
						stmt.setString(1,books[i].getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,id); 
						stmt.executeUpdate();
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return1At='"+output+"' where id_visitors=?;");   
						
						stmtdate.setInt(1,id);
						stmtdate.executeUpdate();
						
						r4 = s4.executeQuery("select datediff(NOW(),(select Return1At from visitors where id_visitors ='"+id+"'));");
						while(r4.next())
							datediff=r4.getInt(1);
						if(datediff>0) {
							PreparedStatement sfine=conn.prepareStatement("Update `test_db`.'visitors' set Fine=Fine+? where visitors_id='"+id+"';");
							sfine.setInt(1,datediff*20);
							sfine.executeUpdate();
						}
						else {
							System.out.println("No fine");
						}
					}
					else if(book_check.getString("book2").equals("two")) {
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book2=? where id_visitors=?;");  
						stmt.setString(1,books[i].getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,id); 
						stmt.executeUpdate();
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return2At='"+output+"'where id_visitors=?;");   
						
						stmtdate.setInt(1,id); 
						stmtdate.executeUpdate();
						
						r4 = s4.executeQuery("select datediff(NOW(),(select Return2At from visitors where id_visitors ='"+id+"'));");
						while(r4.next())
							datediff=r4.getInt(1);
						if(datediff>0) {
							PreparedStatement sfine=conn.prepareStatement("Update `test_db`.'visitors' set Fine=Fine+? where visitors_id='"+id+"';");
							sfine.setInt(1,datediff*20);
							sfine.executeUpdate();
						}
						else {
							System.out.println("No fine");
						}
			
						}
					else if(book_check.getString("book3").equals("three")) {
						PreparedStatement stmt=conn.prepareStatement("update `test_db`.`visitors` set book3=? where id_visitors=?;");  
						stmt.setString(1,books[i].getTitle());//1 specifies the first parameter in the query i.e. name  
						stmt.setInt(2,id); 
						stmt.executeUpdate();
						
						PreparedStatement stmtdate=conn.prepareStatement("update `test_db`.`visitors` set Return3At='"+output+"' where id_visitors=?;");   
						 
						stmtdate.setInt(1,id);
						stmtdate.executeUpdate();
						
						r4 = s4.executeQuery("select datediff(NOW(),(select Return3At from visitors where id_visitors ='"+id+"'));");
						while(r4.next())
							datediff=r4.getInt(1);
						if(datediff>0) {
							PreparedStatement sfine=conn.prepareStatement("Update `test_db`.'visitors' set Fine=Fine+? where visitors_id='"+id+"';");
							sfine.setInt(1,datediff*20);
							sfine.executeUpdate();
						}
						else {
							System.out.println("No fine");
						}
					}
					
					}
				}
			}
	
			 catch (SQLException e) {
				System.out.println(e+ " this is the error");
				e.printStackTrace();
			}
			
			visitor_list.get(id).displaybooks();
			break;
		}
	}
	sc.close();
}
	
	 static void bookreturnfunc(Connection conn,int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Portal for returning of the book");
		System.out.println("Enter the Title of the book::");
		String nbook_title=sc.nextLine();
		nbook_title=sc.nextLine();
		Statement s7 = conn.createStatement();
		Statement s8 = conn.createStatement();
		Statement s9 = conn.createStatement();
		ResultSet r8 = s8.executeQuery("Select * from `test_db`.`visitors` where id_visitors= '"+id+"';");
		System.out.println("Dear User your current books are >>");
		System.out.println(r8.getString("book1")+"\n"+r8.getString("book2")+"\n"+r8.getString("book3"));
		while(r8.next()) {
		if(nbook_title.equals(r8.getString("book1")) || nbook_title.equals(r8.getString("book2")) || nbook_title.equals(r8.getString("book3"))){
			
		s7.executeUpdate("Update `test_db`.`booklist` set stock_quantity=stock_quantity+1 where title='"+nbook_title+"';");
		if(r8.getString("book1").equals(nbook_title)) {
			s9.executeUpdate("Update `test_db`.`visitors` set book1='one'");
			System.out.println("book 1 returned");
		}
		else if(r8.getString("book2").equals(nbook_title)) {
			s9.executeUpdate("Update `test_db`.`visitors` set book2='two'");
			System.out.println("book 2 returned");
		}
		else if(r8.getString("book3").equals(nbook_title)) {
			s9.executeUpdate("Update `test_db`.`visitors` set book3='three'");
			System.out.println("book 3 returned");
		}

		}
		else {
			System.out.println("You Do not have this Book to return");
		}
		}
		sc.close();

	}
	 
	 static void adminbookadd(Connection conn, Administrator admin, Books[] books) throws SQLException {
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Hello you can add a book here...\n");
			System.out.println("Enter the details of the book\n");
			System.out.println("Enter the Title of the book::");
			String nbook_title=sc.nextLine();
			nbook_title=sc.nextLine();
			System.out.println("Enter the first name of the Writer::");
			String nbook_writerfname=sc.next();
			System.out.println("Enter the last name of the Writer::");
			String nbook_writerlname=sc.next();
			System.out.println("Enter the stock of the books");
			int nbook_stock=sc.nextInt();
			System.out.println("Enter the year of release::");
			int nbook_year=sc.nextInt();
			System.out.println("Enter the number of pages::");
			int nbook_pages=sc.nextInt();
			
			num_books=num_books+1;
			
			Books nbook= new Books(nbook_title,nbook_writerfname+nbook_writerlname,nbook_year,nbook_stock,nbook_pages);
			Statement s3 = conn.createStatement();
			admin.AddBook(nbook);
			books[num_books-1] = nbook;
			try {
				s3.executeUpdate("INSERT INTO `test_db`.`booklist` (title, author_fname, author_lname, released_year, stock_quantity, pages) VALUES ('"+nbook_title+"','" +nbook_writerfname+"','"+nbook_writerlname+"','"+nbook_year+"','"+nbook_stock+"','"+nbook_pages+"');");
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			sc.close();
	 }
	 static void adminbookremove(Connection conn) {
		 Scanner sc = new Scanner(System.in); 
		 System.out.println("You have entered the portal for DELETING A VISITOR....");
			System.out.println("Enter the id of the visitor you want to delete>>>");
			int vanish=sc.nextInt();
			try {
				Statement stmt5 = conn.createStatement(); 
				stmt5.executeUpdate("Delete from `test_db`.`visitors` where id_visitors='"+vanish+"';");
				System.out.println(vanish);
				System.out.println("Delete Done");
			}
			catch(SQLException e){
				System.out.println("Delete not done");
			}
			sc.close();
	 }
	 
	 static void adminmail(Connection conn) throws SQLException {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Welcome to the portal for the mailing the visitor\n");
			System.out.println("Enter the id of the visitor to be mailed");
			int mail_id=sc.nextInt();
			Statement s6 = conn.createStatement();
			ResultSet mailrs = s6.executeQuery("select email_id from `test_db`.`visitors` where id_visitors ='"+mail_id+"';");
			while(mailrs.next()) {
				System.out.println("Mail has been sent to the visitor "+mailrs.getString("email_id"));
			}
			sc.close();
	 }
	 
	 static int VisitorChoice(String person,List<Integer> visitorids, Connection conn, Books[] books, List<Visitor> visitor_list) throws SQLException {
		 Scanner scan =  new Scanner(System.in);
				System.out.println("Please Enter Your id>>> \n");
				int flag=0;
				int id=scan.nextInt();
				for(int y:visitorids) {
					if(id==y)
					{
					System.out.println("Welcome....");
					System.out.println("We have recognised you...\n \n");
					flag=1;
					}
				}
					if(flag==0) {					
						System.out.println("Sorry .. wrong id....");
						System.out.println("You are not recognised ...");
						System.out.println("try Registering with our system.\nTHANKYOU");
						System.exit(1);
						}
					
			System.out.println("WHAT DO YOU WISH TO DO\n");
			System.out.println("1.SEARCH BOOKS and ISSUE");
			System.out.println("2.RETURN A BOOK");
			
			int operation=scan.nextInt();
			
			
			switch (operation) {
			case 1:
				Bookissuefunc(id,conn,books,visitor_list);	
				break;
			case 2:
				bookreturnfunc(conn,id); 		
				break;
			default:
				break;
			}
			scan.close();
			return id;
	 }
	 
	static void DisplayBooks(Connection conn,int id) throws SQLException {
		 Statement statement = conn.createStatement();
		 ResultSet r1 =statement.executeQuery("select * from `test_db`.`visitors`;");
			while(r1.next()) {
				if(r1.getInt("id_visitors")==id)
				System.out.println("Your Visitor id is "+r1.getInt("id_visitors") +"\n"+ r1.getString("book1")+"\n" +r1.getString("book2")+"\n"+r1.getString("book3"));
			}
	 }
	 
	 static void AdminChoice(Administrator admin,Connection conn,Books books[]) throws SQLException {
		 Scanner scan = new Scanner(System.in);
		 System.out.println("Welcome to the administrator portal.....");
			System.out.println("Enter the admin password>>>");
			String password=scan.next();
			if(password.equals(admin.password)) {
				System.out.println("Welcome to  the library....");
			}
			else {
				System.out.println("Sorry Wrong Pasword...");
				System.exit(1);
			}
			System.out.println("What do you wish to do ? \n ");
			System.out.println("1. Add a new book");
			System.out.println("2. Remove a visitor");
			System.out.println("3. mail a visitor");
			
			int admin_choice=scan.nextInt();
			
			switch(admin_choice) {
			
			case 1:
				adminbookadd(conn,admin,books);
				break;
			case 2:
				adminbookremove(conn);
				break;
			case 3:
				adminmail(conn);
				break;
			default:
				System.out.println("Enter a valid Operation");
				break;
						}
			scan.close();
		}
	 
	 static void WelcomeMessage(Books books[],Connection conn) throws SQLException {
		Statement statement = conn.createStatement();   //Execute statement object
		ResultSet resultset =statement.executeQuery("select * from `test_db`.`booklist`;");
			
			System.out.println("WELCOME TO THE MODERN LIBRARY \n ");
			System.out.println("CURRENTLY THERE ARE "+num_books+" TITLES \n");
			
			int i=0;
			while(resultset.next()) {
				books[i] =new Books(resultset.getString("title"),
						resultset.getString("author_fname")+" "+resultset.getString("author_lname"),
						resultset.getInt("released_year"), resultset.getInt("stock_quantity"),
						resultset.getInt("pages"));
						i++;
			}
	 }

	@Override
	public void start(Stage arg0) throws Exception {
		
		
	}
	
	 }*/



















