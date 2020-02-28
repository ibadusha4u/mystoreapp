package com.mystore.controller.productinventory;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String VIEW = "/product/viewProduct.jsp";
	private static String DELETE = "/product/deleteProduct.jsp";
	private static String SEARCH = "/product/createProduct.jsp";
	private static String UPDATE = "/product/updateProduct.jsp";
	private static String ERROR = "/error.jsp";

	private ProductService productService;

	
	public ProductController() {
		super();
		productService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");
		try {
			if (action.equalsIgnoreCase("viewProduct")) {
				forward = VIEW;			
				request.setAttribute("products", productService.getAllProduct());
				
			} else if (action.equalsIgnoreCase("addProduct")) {
				forward = SEARCH;
			} else if (action.equalsIgnoreCase("updateProduct")) {
				forward = UPDATE;
				int sku = Integer.parseInt(request.getParameter("sku"));
				Product product = productService.getProductByProdid(sku);
				System.out.println("updateProduct " + product.getName());
				request.setAttribute("product", product);
	
			} else if (action.equalsIgnoreCase("deleteProduct")) {
				forward = DELETE;
				int sku = Integer.parseInt(request.getParameter("sku"));
				Product product = productService.getProductByProdid(sku);
				System.out.println("deleteProduct " + product.getName());
				request.setAttribute("product", product);
			}
		} catch (BusinessException e) {			
			e.printStackTrace();
			forward = ERROR;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		try {
			Product product = new Product();
	
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("Submit")) {
	
				String name = request.getParameter("name");
				double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
	
				product.setName(name);
				product.setQuantity(quantity);
				product.setUnitPrice(unitPrice);
				productService.add(product);
	
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The product has been created');");
				pw.println("window.location.href='/mystore/ProductController?action=viewProduct';");
				pw.println("</script>");
	
			} else if (action.equalsIgnoreCase("Update")) {
	
				int sku = Integer.parseInt(request.getParameter("sku"));
				String name = request.getParameter("name");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
				product.setSku(sku);
				product.setName(name);
				product.setQuantity(quantity);
				product.setUnitPrice(unitPrice);
	
				
					productService.updateProduct(product);
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script>");
					pw.println("alert('The product has been updated');");
					pw.println("window.location.href='/mystore/ProductController?action=viewProduct';");
					pw.println("</script>");
	
			} else if (action.equalsIgnoreCase("Delete")) {
	
				int sku = Integer.parseInt(request.getParameter("sku"));
				productService.deleteProduct(sku);
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The product has been deleted');");
				pw.println("window.location.href='/mystore/ProductController?action=viewProduct';");
				pw.println("</script>");
			}

		} catch (BusinessException e) {			
			e.printStackTrace();
			forward = ERROR;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}

	}

}
