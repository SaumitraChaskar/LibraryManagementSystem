package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSsetterClass {
	
	public Connection CreateConnection() throws SQLException {
	String url ="jdbc:mysql://localhost:3306/test_db?useSSL=false";  	
	Connection conn = DriverManager.getConnection(url,"root","---");
	return conn;
	}
}
