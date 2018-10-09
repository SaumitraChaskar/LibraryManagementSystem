/*package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitorValidation {
		Connection conn;
	public void ValidateVisitor() throws SQLException {
		ConnectionSsetterClass csc=new ConnectionSsetterClass();
		conn =csc.CreateConnection();
		
		int num_books=0;
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
	}
}*/
