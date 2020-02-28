package com.ordermanagement.model;

import java.sql.Date;
import java.util.List;

public class Order {
	
	private int id;	
	private double amount;
	private Date createdDate;
	private List<OrderItem> orderItem;
		
	public Order() {
		super();
	}
	
	public Order(int id, int amount, Date createdDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
	
		
}
