package com.mystore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.core.exception.BusinessException;
import com.mystore.dao.AccountDAO;
import com.mystore.model.Account;
import com.mystore.service.AccountServiceImpl;


public class AccountServiceTest {
	
	@Mock 
	private AccountDAO accountDAO;
	
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;
		
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAccountByAccountId () {		
		 try {			 
            when(accountDAO.get(any(Integer.class))).thenReturn(new Account());	           
            assertThat(accountServiceImpl.getAccountByAccountId(1), is(notNullValue()));			  
        } catch (BusinessException e) {			
			e.printStackTrace();
		}
	}
	
    @Test
    public void testGetAccount() {
    	try {
    		 when(accountDAO.getAccount(any(Account.class))).thenReturn(new Account());
    		 assertThat(accountServiceImpl.getAccount(new Account()), is(notNullValue()));
    	 } catch (BusinessException e) {			
 			e.printStackTrace();
 		}
    }
    
    @Test
    public void testGetAccountByEmail() {
    	try {
    		 when(accountDAO.getAccountByEmail(any(String.class))).thenReturn(new Account());
    		 assertThat(accountServiceImpl.getAccountByEmail(new String()), is(notNullValue()));
    	 } catch (BusinessException e) {			
 			e.printStackTrace();
 		}
    }
}
