package application;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Visitor {
	Integer id;
	String book1;
	String book2;
	String book3;
	Date return1;
	Date return2;
	Date return3;
	Integer fine;
	int book_with_me=0;
	private String password;
	List<Books> b_list = new ArrayList<Books>();
	
	 
	public Visitor(Integer id, String book1, String book2, String book3, Date return1, Date return2, Date return3, int fine,String password) {
		super();
		this.id = id;
		this.book1 = book1;
		this.book2 = book2;
		this.book3 = book3;
		this.return1 = return1;
		this.return2 = return2;
		this.return3 = return3;
		this.fine = fine;
		this.password=password;
		
	}
	

	/*public void insertbook(Books book) {
		book_with_me=book_with_me+1;
		b_list.add(book);
	}
	public void displaybooks() {
		System.out.println("Hello User:");
		System.out.println("Your Today's List Contains>>>");
		for(Books x : b_list) {
			System.out.println(x.title);
		}
	}
	public void removebooks() {
		b_list.remove(book_with_me);
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook1() {
		return book1;
	}

	public void setBook1(String book1) {
		this.book1 = book1;
	}

	public String getBook2() {
		return book2;
	}

	public void setBook2(String book2) {
		this.book2 = book2;
	}

	public String getBook3() {
		return book3;
	}

	public void setBook3(String book3) {
		this.book3 = book3;
	}

	public Date getReturn1() {
		return return1;
	}

	public void setReturn1(Date return1) {
		this.return1 = return1;
	}

	public Date getReturn2() {
		return return2;
	}

	public void setReturn2(Date return2) {
		this.return2 = return2;
	}

	public Date getReturn3() {
		return return3;
	}

	public void setReturn3(Date return3) {
		this.return3 = return3;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getBook_with_me() {
		return book_with_me;
	}

	public void setBook_with_me(int book_with_me) {
		this.book_with_me = book_with_me;
	}

	public List<Books> getB_list() {
		return b_list;
	}

	public void setB_list(List<Books> b_list) {
		this.b_list = b_list;
	}
}
