package com.mystore.service;

import com.core.exception.BusinessException;
import com.mystore.model.Account;

public interface AccountService {
	
	 Account login(Account bean) throws BusinessException;
	 
	 void add(Account bean) throws BusinessException;
	 
	 Account getAccountByEmail(String email) throws BusinessException;
	 
	 Account getAccountByAccountId(int accountId) throws BusinessException;
	 
	 Account getAccount(Account bean) throws BusinessException;
	 
	 Account getAccount() throws BusinessException;
	 
	 void updateAccount(Account bean) throws BusinessException; 
	 
	 void deleteAccount(int accountId) throws BusinessException;

}
