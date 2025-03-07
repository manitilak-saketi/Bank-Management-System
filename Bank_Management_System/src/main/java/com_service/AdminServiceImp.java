package com_service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImp;
import com.DAO.AdminException;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;
import com.model.BankUserDetails;

public class AdminServiceImp implements AdminService {
	AdminDAO ad= new AdminDAOImp();
	BankUserDAO b=new BankUserDAOImp();
	BankUserDetails bb =new BankUserDetails();
	Scanner s=new Scanner(System.in);
	@Override
	public void adminLogin() {
		boolean status=true;
		while(status) {
		try {
			System.out.println("Enter the admin email Id");
			String admin_email_id=s.next();
			System.out.println("Enter the admin password");
			String admin_password=s.next();
			if(ad.getAdminDetailsByUsingEmailAndPassword(admin_email_id, admin_password)) {
				System.out.println("Enter 1 for BankUserDetails \n Enter 2 for AllRequestDetails");
				status=false;
				switch (s.nextInt()) {
				case 1:System.out.println("get all user details");
						List<BankUserDetails> l=b.getAllBankUserDetails();
						l.forEach((user)->{
							System.out.println("Name:"+user.getName());
							System.out.println("Email:"+user.getEmail());
						});
					break;
				case 2:System.out.println("get all account requst details");
				allAcountRequestDetails();
					break;

				default:
					break;
				}
			}
			else {
				throw new AdminException("Invalid emailId and password");
			}
		} catch (AdminException e) {
			System.out.println(e.getMsg());
		}
		}
		
	}

	@Override
	public void allAcountRequestDetails() {
		List<BankUserDetails>l=b.getAllBankUserDetails();
		List<BankUserDetails>pending=l.stream().filter(user->user.getStatus().equalsIgnoreCase("pending"))
				.collect(Collectors.toList());
		pending.forEach((user)->{
			int index=pending.indexOf(user)+1;
			System.out.println("S.no:"+index);
			System.out.println("User Name:"+user.getName());
			System.out.println("User mobileNo:"+user.getMobileNo());
			System.out.println("User AadharNo:"+user.getAadharNo());
			System.out.println("User Status:"+user.getStatus());
		});
		System.out.println("Enter S.No to select User");
		BankUserDetails b=pending.get(s.nextInt()-1);
		System.out.println(b);
		System.out.println("Enter 1 for Accept \n Enetr 2 for Reject");
		switch (s.nextInt()) {
		case 1:
			acceptRegistration(b.getAadharNo());
			break;
		case 2:
			System.out.println("Rejected");
			break;
		default:
			break;
		}
	}

	@Override
	public void acceptRegistration(long aadharNo) {
		Random r=new Random();
		int pin=r.nextInt(1000);
		if(pin<1000)
			pin+=1000;
			int accountNo=r.nextInt(1000000);
			if(accountNo<100000)
				accountNo+=1000000;
				int res=b.updatePinAndAccountNoAndStatusByUsingAadharNo(pin, accountNo, aadharNo);
				if(res!=0)
					System.out.println("Accepted \n AccountNo:"+accountNo+"\n pin:"+pin);
				else
					System.out.println("Server not found");
			}
}
