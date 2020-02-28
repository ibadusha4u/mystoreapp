package com.ordermanagement.dao;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.core.dao.AbstractDAO;
import com.core.exception.BusinessException;
import com.ordermanagement.model.Order;
import com.ordermanagement.model.OrderItem;

import com.core.util.ConnectionManager;

public class OrderDAO implements AbstractDAO<Order> {

	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private static Statement stmt = null;

	@Override
	public Order get(int orderid) throws BusinessException {
		Order order = new Order();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"select order_item_id, product_sku, unit_price, sold_quantity, order_id from ORDER_ITEM where order_id=?");

			ps.setInt(1, orderid);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderItemId(rs.getInt(1));
				orderItem.setProductSku(rs.getInt(2));
				orderItem.setUnitPrice(rs.getDouble(3));
				orderItem.setSoldQuantity(rs.getInt(4));
				orderItem.setOrderId(rs.getInt(5));
				orderItemList.add(orderItem);
			}
			order.setOrderItem(orderItemList);
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
		return order;
	}

	@Override
	public List<Order> getAll() throws BusinessException{
		List<Order> orderList = new ArrayList<Order>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q1 = "select id, amount, created_date from `ORDER`";
			System.out.println(q1);
			ResultSet rs1 = stmt.executeQuery(q1);

			while (rs1.next()) {
				Order order = new Order();
				order.setId(rs1.getInt(1));
				order.setAmount(rs1.getDouble(2));
				order.setCreatedDate(rs1.getDate(3));

				List<OrderItem> orderItemList = new ArrayList<OrderItem>();
				ps = currentCon.prepareStatement(
						"select order_item_id, product_sku, unit_price, sold_quantity from ORDER_ITEM where order_id = ?");
				ps.setInt(1, order.getId());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderItemId(rs.getInt(1));
					orderItem.setProductSku(rs.getInt(2));
					orderItem.setUnitPrice(rs.getDouble(3));
					orderItem.setSoldQuantity(rs.getInt(4));
					orderItemList.add(orderItem);
				}
				order.setOrderItem(orderItemList);
				orderList.add(order);
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

		return orderList;
	}

	@Override
	public void update(Order order) throws BusinessException{

		OrderItem orderItem = order.getOrderItem().get(0);
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.FLOOR);

		double amount = new Double(df.format(orderItem.getSoldQuantity() * orderItem.getUnitPrice()));
		String searchQuery = "UPDATE `ORDER` INNER JOIN ORDER_ITEM on `ORDER`.id = ORDER_ITEM.order_id"
				+ " SET ORDER_ITEM.sold_quantity = '" + orderItem.getSoldQuantity() + "', `ORDER`.amount='" + amount
				+ "' WHERE ORDER_ITEM.order_id= " + orderItem.getOrderId() + "";

		System.out.println("Query : " + searchQuery);
		try {
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

	@Override
	public void delete(int orderid) throws BusinessException{
		try {
			currentCon = ConnectionManager.getConnection();
			String query = "delete from ORDER_ITEM where order_id =?";
			ps = currentCon.prepareStatement(query);
			System.out.println(query);

			ps.setInt(1, orderid);
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

	@Override
	public void add(Order t) throws BusinessException{
		// TODO Auto-generated method stub
	}

	public void add(List<Order> orderLst) throws BusinessException{

		// TODO: Do Batch processing of all Items in the order
		Order order = orderLst.get(0);
		for (OrderItem orderItem : order.getOrderItem()) {

			int productSku = orderItem.getProductSku();
			int soldQuantity = orderItem.getSoldQuantity();
			double unitPrice = orderItem.getUnitPrice();
			double totalAmount = soldQuantity * unitPrice;
			int orderId = 0;

			try {
				currentCon = ConnectionManager.getConnection();
				ps = currentCon.prepareStatement("insert into `ORDER` (amount, created_date) values (?,sysdate())",
						Statement.RETURN_GENERATED_KEYS);
				ps.setDouble(1, totalAmount);
				int affectedRows = ps.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("insertion failed, no rows affected.");
				}
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);

					ps = currentCon.prepareStatement(
							"insert into ORDER_ITEM (sold_quantity, unit_price, product_sku, order_id)values(?,?,?,?)");
					ps.setInt(1, soldQuantity);
					ps.setDouble(2, unitPrice);
					ps.setInt(3, productSku);
					ps.setInt(4, orderId);
					ps.executeUpdate();
				} else {
					throw new SQLException("Insertion failed, no ID obtained.");
				}

				System.out.println("Your product Sku is " + productSku);
				System.out.println("Your order product quantity is " + soldQuantity);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new BusinessException(ex.getMessage(), ex);
			}

			finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
					}
					ps = null;
				}
			}
		}
	}

}
