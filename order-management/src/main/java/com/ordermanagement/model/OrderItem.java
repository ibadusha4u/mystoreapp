package com.ordermanagement.model;

public class OrderItem {
	
	private int orderItemId;
	private int soldQuantity;
	private double unitPrice;
	private int productSku;
	private int orderId;
		
	public OrderItem() {
		super();
	}
	
	public OrderItem(int orderItemId, int soldQuantity, double unitPrice, int productSku, int orderId) {
		super();
		this.orderItemId = orderItemId;
		this.soldQuantity = soldQuantity;
		this.unitPrice = unitPrice;
		this.productSku = productSku;	
		this.orderId = orderId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getProductSku() {
		return productSku;
	}

	public void setProductSku(int productSku) {
		this.productSku = productSku;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
		
	}
