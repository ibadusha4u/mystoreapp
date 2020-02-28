package com.inventory.service;

import java.util.List;

import com.core.exception.BusinessException;
import com.inventory.model.Product;

public interface ProductService {

	void add (Product product) throws BusinessException;
		
	void updateQuantity(Product product) throws BusinessException;
	
	void updateProduct(Product product) throws BusinessException;
	
	List<Product> getAllProduct() throws BusinessException;
	
	Product getProductByProdid(int sku) throws BusinessException;
	
	void deleteProduct(int sku) throws BusinessException;	
	
}
