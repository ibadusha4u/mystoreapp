package com.mystore.controller.ordermanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.exception.BusinessException;
import com.inventory.model.Product;
import com.inventory.service.ProductService;
import com.inventory.service.ProductServiceImpl;
import com.ordermanagement.model.Order;
import com.ordermanagement.model.OrderItem;
import com.ordermanagement.service.OrderService;
import com.ordermanagement.service.OrderServiceImpl;

/**
 * OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String CREATE = "/order/addOrder.jsp";
	private static String UPDATE = "/order/updateOrder.jsp";
	private static String LIST = "/order/listOrder.jsp";
	private static String UPLOAD = "/order/uploadExcel.jsp";
	private static String ERROR = "/error.jsp";

	private OrderService orderService;

	private ProductService productService;
	
	public OrderController() {
		super();
		orderService = new OrderServiceImpl();
		productService = new ProductServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String forward = "";
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("createOrder")) {
				forward = CREATE;
				List<Product> product = productService.getAllProduct();
				request.setAttribute("products", product);
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
				
			} else if (action.equalsIgnoreCase("updateOrder")) {
				forward = UPDATE;
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				Order order = orderService.getOrderById(orderid);
				List<Product> product = productService.getAllProduct();
				System.out.println(order.getOrderItem().get(0).getProductSku());
				request.setAttribute("order", order.getOrderItem().get(0));
				request.setAttribute("products", product);
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
				
			} else if (action.equalsIgnoreCase("deleteOrder")) {
				int orderid = Integer.parseInt(request.getParameter("orderid"));				
				System.out.println("orderid : " + orderid);
				orderService.deleteOrder(orderid);
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The order has been deleted');");
				pw.println("window.location.href='/mystore/OrderController?action=listOrder';");
				pw.println("</script>");
				
			} else if (action.equalsIgnoreCase("listOrder")) {
				forward = LIST;
				List<Product> product = productService.getAllProduct();
				List<Order> order = orderService.getAllOrders();
				request.setAttribute("order", order);
				request.setAttribute("products", product);
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
	
			} else if (action.equalsIgnoreCase("uploadExcel")) {
				forward = UPLOAD;
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
			}
		} catch (BusinessException e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Order order = new Order();
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			List<Order> orderList = new ArrayList<Order>();

			String action = request.getParameter("action");

			if (action.equalsIgnoreCase("Submit")) {

				String prod = request.getParameter("prod");
				String[] str = prod.split(" ");
				int productSku = Integer.parseInt(str[0]);
				double unitPrice = Double.parseDouble(str[1]);
				int soldQuantity = Integer.parseInt(request.getParameter("quantity"));

				OrderItem orderItem = new OrderItem();
				orderItem.setProductSku(productSku);
				orderItem.setSoldQuantity(soldQuantity);
				orderItem.setUnitPrice(unitPrice);
				orderItemList.add(orderItem);
				order.setOrderItem(orderItemList);
				orderList.add(order);
				orderService.add(orderList);

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The order has been created');");
				pw.println("window.location.href='/mystore/OrderController?action=listOrder';");
				pw.println("</script>");
				
			} else if (action.equalsIgnoreCase("Update")) {

				int sku = Integer.parseInt(request.getParameter("productSku"));
				int quantity = Integer.parseInt(request.getParameter("soldQuantity"));
				double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
				int orderId = Integer.parseInt(request.getParameter("orderId"));

				OrderItem orderItem = new OrderItem();
				orderItem.setProductSku(sku);
				orderItem.setSoldQuantity(quantity);
				orderItem.setUnitPrice(unitPrice);
				orderItem.setOrderId(orderId);
				orderItemList.add(orderItem);
				order.setOrderItem(orderItemList);
				orderService.updateOrder(order);

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The product has been updated');");
				pw.println("window.location.href='/mystore/OrderController?action=listOrder';");
				pw.println("</script>");

			}
		} catch (BusinessException e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}

	}

}
