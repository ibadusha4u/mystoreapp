package com.mystore.controller.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.exception.BusinessException;
import com.mystore.model.Account;
import com.mystore.service.AccountService;
import com.mystore.service.AccountServiceImpl;

/**
 *  LoginController
 */

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String ERROR = "/error.jsp";

	private AccountService accountService;
	
	public LoginController() {
		super();
		accountService = new AccountServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			HttpSession session = request.getSession(true);
			if (action.equalsIgnoreCase("Logout")) { // logout
				session.setAttribute("currentSessionUser", null);
				session.setAttribute("currentSessionUserRole", null);
				session.invalidate();
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				// pw.println("alert('Logout Success');");
				pw.println("window.location.href='/mystore/login.jsp';");
				pw.println("</script>");
			}
		} catch (Exception e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Account account = new Account();
			account.setEmail(email);
			account.setPassword(password);
			account = accountService.login(account);

			if (account.isValid()) {
				account = accountService.getAccountByEmail(email);
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", account.getEmail());
				session.setAttribute("currentSessionUserRole", account.getRole());
				session.setAttribute("currentSessionUserName", account.getName());
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Login Successful');");
				pw.println("window.location.href='/mystore/index.jsp';");
				pw.println("</script>");
			} else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Incorrect Email or Password');");
				pw.println("window.location.href='/mystore/login.jsp';");
				pw.println("</script>");
			}
		} catch (BusinessException e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}

	}
}
