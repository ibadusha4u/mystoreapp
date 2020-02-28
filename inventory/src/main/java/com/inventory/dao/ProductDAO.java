package com.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventory.model.Product;
import com.core.dao.AbstractDAO;
import com.core.exception.BusinessException;
import com.core.util.ConnectionManager;

public class ProductDAO implements AbstractDAO<Product> {

	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private static java.sql.Statement stmt = null;

	public void add(Product bean) throws BusinessException{

		String name = bean.getName();
		int quantity = bean.getQuantity();
		double unitPrice = bean.getUnitPrice();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("insert into product(name, quantity, unit_price)values(?,?,?)");
			ps.setString(1, name);
			ps.setDouble(2, quantity);
			ps.setDouble(3, unitPrice);
			ps.executeUpdate();

			System.out.println("Your product name is " + name);
			System.out.println("Your product quantity is " + quantity);
			System.out.println("Your product price is " + unitPrice);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}
	}

	public Product get(int id) throws BusinessException{
		Product product = new Product();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from product where sku= ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				product.setSku(rs.getInt("sku"));
				product.setName(rs.getString("name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUnitPrice(rs.getDouble("unit_price"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
		}

		return product;
	}

	public List<Product> getAll() throws BusinessException{
		List<Product> products = new ArrayList<Product>();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setSku(rs.getInt("sku"));
				product.setName(rs.getString("name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUnitPrice(rs.getDouble("unit_price"));
				products.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
		}

		return products;
	}

	public void update(Product product) throws BusinessException{
		try {
			int sku = product.getSku();
			String name = product.getName();
			int quantity = product.getQuantity();
			double unitPrice = product.getUnitPrice();
			String searchQuery = "UPDATE product SET name='" + name + "', quantity = '" + quantity + "', unit_Price='"
					+ unitPrice + "' WHERE sku= '" + sku + "'";
		
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
		}
	}

	public void delete(int id) throws BusinessException{
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("delete from product where sku=?");
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}
	}

	public void updateQuantity(Product bean) throws BusinessException{
		try {
			int sku = bean.getSku();
			int quantity = bean.getQuantity();
			String searchQuery = "UPDATE product SET quantity = '" + quantity + "' WHERE sku= '" + sku + "'";		
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
		}
	}

}
