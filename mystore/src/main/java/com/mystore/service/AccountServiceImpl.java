package com.mystore.service;

import com.core.exception.BusinessException;
import com.mystore.dao.AccountDAO;
import com.mystore.model.Account;

public class AccountServiceImpl implements AccountService {
	
	private AccountDAO accountDAO;

	public AccountServiceImpl() {
		super();
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
		return accountDAO.login(bean);
	}
	
	@Override
	public Account getAccountByEmail(String email) throws BusinessException {		
		return accountDAO.getAccountByEmail(email);
	}

	@Override
	public Account getAccount(Account bean)  throws BusinessException {		
		return accountDAO.getAccount(bean);
	}

	@Override
	public Account getAccount() throws BusinessException {		
		return accountDAO.getAccount();
	}
	
}
