package com.ordermanagement.service;

import java.util.List;

import com.core.exception.BusinessException;
import com.ordermanagement.model.Order;

public interface OrderService {
	
	void add(List<Order> orderLst) throws BusinessException;
	
	List<Order> getAllOrders() throws BusinessException;
	
	Order getOrderById(int orderid) throws BusinessException;
	
	void updateOrder(Order order) throws BusinessException;
	
	void deleteOrder(int orderid) throws BusinessException;

}
