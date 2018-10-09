package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayBooks {
	public void DBooks() throws SQLException {
		int i=0;
		ConnectionSsetterClass csc = new ConnectionSsetterClass();
		Connection newConnection = null;
		try {
		newConnection = csc.CreateConnection();
		} catch (SQLException e) {
			System.out.println("Error Occured\n");
			e.printStackTrace();
		}
		//SQL Query and Result
		
		Statement s =newConnection.createStatement();
		ResultSet r =s.executeQuery("select * from `test_db`.`booklist`;");
		while(r.next()) {
			System.out.println((i+1)+" Book is "+r.getString("title"));
			i++;
		}
		
	}
}	
