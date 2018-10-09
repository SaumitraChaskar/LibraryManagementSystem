package application;

public class Administrator {

		int id;
		static String password="admin123";
		public Administrator(int id) {
			super();
			this.id = id;
		}
		
		public void AddBook(Books book) {
			System.out.println(book.getTitle() + " is added");
		}
	}
