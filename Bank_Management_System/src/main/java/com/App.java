package com;

import java.util.Scanner;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImp;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;

import com_service.AdminService;
import com_service.AdminServiceImp;
import com_service.BankService;
import com_service.BankServiceImplementation;

/**
 * Hello world!
 *
 */
public abstract class App implements Runnable
{
    public static void main( String[] args ) {
    	Scanner s=new Scanner(System.in);
    	BankService b=new BankServiceImplementation();
    	BankUserDAO bu=new BankUserDAOImp();
    	AdminService as=new AdminServiceImp();
    	AdminDAO ad=new AdminDAOImp();
    	String heading="welcome to bank \n";
    		b.forSleep(heading);
    	boolean start =true;
    	while(start) {
        	System.out.println("Enter 1 for user login \n Enter 2 for Admin login \n Enter 3 for user registration");
        	switch (s.nextInt()) {
    		case 1:
    			String heading2="user login \n";
    			b.forSleep(heading2);
    			b.userLogin();
    			break;
    		case 2:
    			String heading3="Admin login \n";
    			b.forSleep(heading3);
    			as.adminLogin();    			
    			break;
    		case 3:
    			System.out.println("user registration");
    			b.userRegistration();
    			break;
    		default:
    			System.out.println("Invalid option entered");
    			break;	
    	}
        	System.out.println("press yes to continue");
        	System.out.println("press no to exit");
        	String end=s.next();
        	if(end.equalsIgnoreCase("no")) {
        		start=false;
        		String heading1="thank you....";
        		b.forSleep(heading1);
        	}
        	
  
    	}
       

    }
}
