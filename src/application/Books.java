package application;

import java.util.Date;

public class Books {
	String title;
	String name;
	int released_year;
	int stock;
	int pages;
	int number;
	Date date;

	
	public Books(String title, String name, int released_year, int stock, int pages) {
		//super();
		this.title = title;
		this.name = name;
		this.released_year = released_year;
		this.stock = stock;
		this.pages = pages;
	}
	
	public Books(String title,Date date) {
		System.out.println("I am called");
		this.title=title;
		this.date=date;
	}
	public String getTitle() {
		return title;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}
	public int getReleased_year() {
		return released_year;
	}
	public int getStock() {
		return stock;
	}
	public int getPages() {
		return pages;
	}

	public void setStock(int n) {
		this.stock=n;
	}
}