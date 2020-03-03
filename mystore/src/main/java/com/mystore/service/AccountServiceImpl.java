package com.mystore.service;

import com.core.dao.AbstractDAO;
import com.core.exception.BusinessException;
import com.mystore.dao.AccountDAO;
import com.mystore.model.Account;

public class AccountServiceImpl implements AccountService {
	
	private AbstractDAO<Account> accountDAO;

	public AccountServiceImpl() {		
		accountDAO = new AccountDAO();		
	}

	@Override
	public void add(Account bean) throws BusinessException {
		accountDAO.add(bean);		
	}
	
	@Override
	public void updateAccount(Account bean) throws BusinessException {
		accountDAO.update(bean);		
	}
	
	@Override
	public Account getAccountByAccountId(int accountId)  throws BusinessException{		
		return accountDAO.get(accountId);
	}

	@Override
	public void deleteAccount(int accountId) throws BusinessException {
		accountDAO.delete(accountId);		
	}

	@Override
	public Account login(Account bean) throws BusinessException {		
		return ((AccountDAO) accountDAO).login(bean);
	}
	
	@Override
	public Account getAccountByEmail(String email) throws BusinessException {		
		return ((AccountDAO) accountDAO).getAccountByEmail(email);
	}

	@Override
	public Account getAccount(Account bean)  throws BusinessException {		
		return ((AccountDAO) accountDAO).getAccount(bean);
	}

	@Override
	public Account getAccount() throws BusinessException {		
		return ((AccountDAO) accountDAO).getAccount();
	}
	
}
