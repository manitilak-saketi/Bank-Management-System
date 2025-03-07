package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.BankUserDetails;

public class BankUserDAOImp implements BankUserDAO {
	
	private static final String insert=" insert into bank_user_details (name, email_id, Aadhar_no, mobile_no, pan_no, address, gender, amount,status)"
			+ "values(?,?,?,?,?,?,?,?,?)";
	private static final String url="jdbc:mysql://localhost/teca_63?user=root&password=12345"; 
	private static final String update="update bank_user_details set pin=?,account_no=?, status=? where aadhar_no=?";
	private static final String user_login="select * from bank_user_details where (email_id=? or account_no=?) and pin=?";
	private static final String input="select * from bank_user_details";
	private static final String update_acc_balance="update bank_user_details set amount=? where pin=?";
	private static final String remove="delete from bank_user_details where(account_No=? or aadhar_No=?) and pin=? ";
	@Override
	
	public void insertBankUserDetails(BankUserDetails bud) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareCall(insert);
			ps.setString(1, bud.getName());
			ps.setString(2, bud.getEmail());
			ps.setLong(3, bud.getAadharNo());
			ps.setLong(4, bud.getMobileNo());
			ps.setString(5, bud.getPanNo());
			ps.setString(6, bud.getAddress());
			ps.setString(7, bud.getGender());
			ps.setDouble(8, bud.getAmount());
			ps.setString(9, "pending");
			int res=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	@Override
	public List<BankUserDetails> getAllBankUserDetails() {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(input);
			ResultSet rs=ps.executeQuery();
			List<BankUserDetails>l=new ArrayList<BankUserDetails>();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					BankUserDetails bud=new BankUserDetails();	
					bud.setName(rs.getString("name"));
					bud.setAadharNo(rs.getLong("Aadhar_no"));
					bud.setMobileNo(rs.getLong("mobile_no"));
					bud.setEmail(rs.getString("email_id"));
					bud.setStatus(rs.getNString("status"));
					bud.setPanNo(rs.getString("pan_no"));
					l.add(bud);
				}
				return l;
			}
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;		
		}
	}
	@Override
	public int updatePinAndAccountNoAndStatusByUsingAadharNo(int pin,int accountNo,long aadharNo) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(update);
			ps.setInt(1, pin);
			ps.setInt(2, accountNo);
			ps.setString(3, "Accepted");
			ps.setLong(4, aadharNo);

			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public BankUserDetails getUserDetailsUsingEmailIdOrAccountNoAndPin(String email_acc,int pin) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(user_login);
			ps.setString(1, email_acc);
			ps.setString(2,email_acc);
			ps.setInt(3, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				BankUserDetails b=new BankUserDetails();
				b.setAmount(rs.getDouble("amount"));
				b.setPin(rs.getInt("pin"));
				b.setId(rs.getInt("id"));
				return b;
			}
			return null;
		} catch (SQLException e) {
			
		}
		return null;
		
		
	}
	@Override
	public int updateAmountByUsingPin(int pin,double balanceAmount) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(update_acc_balance);
			ps.setDouble(1, balanceAmount);
			ps.setInt(2, pin);
		return ps.executeUpdate();
		} catch (Exception e) {
			return 0;
		}	
	}
	@Override
	public void removeAccount(int pin, String accOrAadhar) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(remove);
			
			ps.setString(1, accOrAadhar);
			ps.setString(2, accOrAadhar);
			ps.setInt(3, pin);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}