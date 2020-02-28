package com.inventory.model;

public class Product {
	
	private int sku;
	private String name;
	private int quantity;
	private double unitPrice;
	
	public Product() {
		super();
	}
	
	public Product(int sku, String name, int quantity, double unitPrice) {
		super();
		this.sku = sku;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	}
