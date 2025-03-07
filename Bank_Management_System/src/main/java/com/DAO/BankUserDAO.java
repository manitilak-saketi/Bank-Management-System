package com.DAO;

import java.util.List;

import com.model.BankUserDetails;

public interface BankUserDAO {
	void insertBankUserDetails(BankUserDetails bud);
	List<BankUserDetails> getAllBankUserDetails();
	int updatePinAndAccountNoAndStatusByUsingAadharNo(int pin,int accountNo,long aadharNo);
	BankUserDetails getUserDetailsUsingEmailIdOrAccountNoAndPin(String email_acc,int pin);
	int updateAmountByUsingPin(int pin,double balanceAmount);
	void removeAccount(int pin,String accOrAadhar);
	
	
}
