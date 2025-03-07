package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImp implements AdminDAO {
	private static final String inp="select * from admin where admin_email_id=? and admin_password=?";
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=12345";
	
	@Override
	public boolean getAdminDetailsByUsingEmailAndPassword(String emailId,String password) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(inp);
			ps.setString(1, emailId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) 
				return true;
			return false;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

