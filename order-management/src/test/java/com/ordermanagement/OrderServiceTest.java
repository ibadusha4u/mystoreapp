package com.ordermanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.core.exception.BusinessException;
import com.ordermanagement.dao.OrderDAO;
import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderServiceImpl;


public class OrderServiceTest {
	
	@Mock 
	private OrderDAO orderDAO;
	
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
		
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetOrderById () {		
		 try {			 
            when(orderDAO.get(any(Integer.class))).thenReturn(new Order());	           
            assertThat(orderServiceImpl.getOrderById(0), is(notNullValue()));			  
        } catch (BusinessException e) {			
			e.printStackTrace();
		}
	}
	
    @Test
    public void testGetAllOrders() {
    	try {
    		 when(orderDAO.getAll()).thenReturn(new ArrayList<Order>());
    		 assertThat(orderServiceImpl.getAllOrders(), is(notNullValue()));
    	 } catch (BusinessException e) {			
 			e.printStackTrace();
 		}
    }
}
