package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Teacher extends user {
	
	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Teacher() {
	
	}
	public Teacher(int id, String username, String userpassword, String personname, String usertype) {
		super(id, username, userpassword, personname, usertype);
		
	}
	
	


}
