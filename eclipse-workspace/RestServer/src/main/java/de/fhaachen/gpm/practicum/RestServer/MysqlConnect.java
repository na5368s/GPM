package de.fhaachen.gpm.practicum.RestServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnect {
	private static final String hostname = "localhost"; 
	private static final String port = "3306"; 
	private static final String user = "root"; 
	private static final String password = "gpm17"; 
	private Connection conn = null;
	
	MysqlConnect(){		
        try { 
	    System.out.println("* Treiber laden"); 
      	    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
        } 
        catch (Exception e) { 
            System.err.println("Unable to load driver."); 
            e.printStackTrace(); 
        } 
	}
	
	public void connect(String dbname) {
		try { 
		    System.out.println("* Verbindung aufbauen"); 
		    String url = "jdbc:mysql://"+hostname+":"+port+"/"+dbname; 
		    conn = DriverManager.getConnection(url, user, password); 
        } 
        catch (SQLException sqle) { 
            System.out.println("SQLException: " + sqle.getMessage()); 
            System.out.println("SQLState: " + sqle.getSQLState()); 
            System.out.println("VendorError: " + sqle.getErrorCode()); 
            sqle.printStackTrace(); 
        } 
	}
	
	public void disconnect() {
		try { 
			System.out.println("* Verbindung schließen");
		    conn.close();
        } 
        catch (SQLException sqle) { 
            System.out.println("SQLException: " + sqle.getMessage()); 
            System.out.println("SQLState: " + sqle.getSQLState()); 
            System.out.println("VendorError: " + sqle.getErrorCode()); 
            sqle.printStackTrace(); 
        } 
	}
	
	public Connection getConnection() {
		return this.conn;
	}

}

