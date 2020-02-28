package com.inventory.service;

import java.util.List;

import com.core.exception.BusinessException;
import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
        
    public ProductServiceImpl() {
            super();
            productDAO = new ProductDAO();
      }
    
    public ProductServiceImpl(ProductDAO productDAO) {     
        this.productDAO = productDAO;
  }
    
    public void add(Product product) throws BusinessException {
		try {
			productDAO.add(product);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}		
	}

	public void updateQuantity(Product product) throws BusinessException {
		try {
			productDAO.updateQuantity(product);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}		
	}

	public void updateProduct(Product product) throws BusinessException {
		try {
			productDAO.update(product);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}		
	}

	public List<Product> getAllProduct() throws BusinessException {	
		
		List<Product> productList =null;
		try {
			productList = productDAO.getAll();
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}
		return productList;
	}

	public Product getProductByProdid(int sku) throws BusinessException{	
		
		Product product = null;
		try {
			product = productDAO.get(sku);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}
		return product;
	}

	public void deleteProduct(int sku) throws BusinessException{
		try {
			productDAO.delete(sku);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}		
	}

	

}
