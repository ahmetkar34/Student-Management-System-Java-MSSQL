package Helper;

import java.sql.*;


public class DBConnection {
	
	Connection c=null;
	
	public DBConnection() {
		
	}
	
	public Connection connDB() {
		
		try {
			this.c=DriverManager.getConnection("jdbc:sqlserver://*****************\\SQLEXPRESS;databaseName=StudentManagementSystemDB;integratedSecurity=true;encrypt=false");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

}
