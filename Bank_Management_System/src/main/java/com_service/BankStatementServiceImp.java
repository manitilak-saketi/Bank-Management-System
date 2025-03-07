package com_service;

import com.DAO.BankStatementDAO;
import com.DAO.BankStatementDAOImp;
import com.model.BankStatement;

public class BankStatementServiceImp implements BankStatementService{

	@Override
	public int bankStatementDetails(BankStatement bs) {
		BankStatementDAO bsd=new BankStatementDAOImp();
		return bsd.insertBankStatement(bs);
		
	}

}
