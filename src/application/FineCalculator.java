package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FineCalculator {
	
	public void CalculateFine() throws SQLException {
		Date now = new Date();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(dateFormatter.format(now));
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE,7); // Adding 7 days
		String output = dateFormatter.format(c.getTime());
		 
		ConnectionSsetterClass csc = new ConnectionSsetterClass();
		Connection conn=csc.CreateConnection();
		
		int datediff=0;
		
		Statement s1 = conn.createStatement();
		Statement statement = conn.createStatement();
		Statement s4 = conn.createStatement();
		Statement s5 = conn.createStatement();
		ResultSet r4 = null;
		
		Statement s = conn.createStatement();
		ResultSet bookshere = s.executeQuery("select * from `test_db`.`visitors`;");
		
		
		while(bookshere.next()) {
			datediff=0;
			int fine=bookshere.getInt("Fine");
			Date book1return=bookshere.getDate("Return1At");
			Date book2return=bookshere.getDate("Return2At");
			Date book3return=bookshere.getDate("Return3At");
			String book1=bookshere.getString("book1");
			String book2=bookshere.getString("book2");
			String book3=bookshere.getString("book3");
			int id =bookshere.getInt("id_visitors");
			
			if(!book1.equals("-1")) {
				r4=s4.executeQuery("select datediff(NOW(),(select Return1At from visitors where id_visitors ='"+id+"'));");
				if(r4!= null) {	
				
				while(r4.next())
					datediff=r4.getInt(1);
				if(datediff>0) {
					PreparedStatement sfine=conn.prepareStatement("Update `test_db`.`visitors` set `Fine`=? where id_visitors = ?;");
					Statement sample = conn.createStatement();
					ResultSet samplers = sample.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+id+"';");
					while(samplers.next()) {
						System.out.println();
					}
					sfine.setInt(1, datediff*20);
					sfine.setInt(2, id);
					int affect=sfine.executeUpdate();
					System.out.println("Something");
					System.out.println(datediff*20);
					System.out.println(affect);
				}
				}
			}
			
			if(!book1.equals("-2")) {
				r4=s4.executeQuery("select datediff(NOW(),(select Return2At from visitors where id_visitors ='"+id+"'));");
				if(r4!=null) {
				while(r4.next())
					datediff=r4.getInt(1);
				if(datediff>0) {
					PreparedStatement sfine=conn.prepareStatement("Update `test_db`.`visitors` set `Fine`=? where id_visitors=?;");
					sfine.setInt(1,datediff*20);
					sfine.setInt(2,id);
					
					sfine.executeUpdate();
				}
				
				}
			}
			
			if(!book1.equals("-3")) {
				r4=s4.executeQuery("select datediff(NOW(),(select Return3At from visitors where id_visitors ='"+id+"'));");
				if(r4!=null) {
				while(r4.next())
					datediff=r4.getInt(1);
				if(datediff>0) {
					PreparedStatement sfine=conn.prepareStatement("Update `test_db`.`visitors` set `Fine`=? where id_visitors=?;");
					sfine.setInt(1,datediff*20);
					sfine.setInt(2,id);
					
					sfine.executeUpdate();
				}
				
			}
			}
		}
		
		
	}
}
