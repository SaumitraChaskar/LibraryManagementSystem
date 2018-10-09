package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FineWindowController implements Initializable {

    @FXML
    private Button back;
    
    @FXML
    private Button calculate;

    @FXML
    private Label finepending;

    @FXML
    private TextField fine;

    @FXML
    private Button pay;
    
    @FXML
    private Label visitorid;
    
    
    
    String SetText(String Id) {
    	visitorid.setText(Id);
    	System.out.println(visitorid.getText());
    	return Id;
    }

    @FXML
    void GoBack(ActionEvent event) {

    }

    
    @FXML
    void CalculateClicked(ActionEvent event) {
    	
		
    	
    	
    	ResultSet rs=null;
		try {
			ConnectionSsetterClass csc = new ConnectionSsetterClass();
			Connection conn=csc.CreateConnection();
			
			
			Statement s=conn.createStatement();
			System.out.println(visitorid);
			System.out.println("Yeh hai id::"+visitorid.getText());
			rs = s.executeQuery("select * from `test_db`.`visitors` where id_visitors='"+visitorid.getText()+"'");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    	try {
			while(rs.next()) {
				System.out.println("Inside assign");
				String fp=rs.getString("Fine");
				finepending.setText(fp);
				System.out.println(fp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    @FXML
    void PayButtonClicked(ActionEvent event) throws SQLException {
    	
    		ConnectionSsetterClass csc = new ConnectionSsetterClass();
    		Connection conn=csc.CreateConnection();
    		
    		int balance=Integer.parseInt(finepending.getText())-Integer.parseInt(fine.getText());
    		
    		PreparedStatement ps = conn.prepareStatement("update `test_db`.`visitors` set Fine=? where id_visitors=? ;");
    		ps.setInt(1, balance);
    		ps.setString(2, visitorid.getText());
    		ps.executeUpdate();
    		System.out.println(balance);
    		System.out.println("paid");
    	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		}
	}

