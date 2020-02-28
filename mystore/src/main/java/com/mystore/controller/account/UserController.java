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
 *  UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    private String VIEW ="/user/viewAccount.jsp";   
    private static String UPDATE = "/user/updateAccount.jsp";
    private static String UPDATEPASS = "/user/updatePass.jsp";
    private static String ERROR = "/error.jsp";
       
    private AccountService accountService;
   
    public UserController() {
       super();
       accountService = new AccountServiceImpl();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			String forward = "";
			if(action.equalsIgnoreCase("viewAccount")) {
				forward = VIEW;
				String email = request.getParameter("email");
				Account account = accountService.getAccountByEmail(email);	
				request.setAttribute("account", account);
			}		
			else if (action.equalsIgnoreCase("updateAccount")){
				forward = UPDATE;
	        	String email = request.getParameter("email");
	        	Account account = accountService.getAccountByEmail(email);        	        	
	        	request.setAttribute("account", account);
			}
			else if (action.equalsIgnoreCase("updatePass")){
				forward = UPDATEPASS;
	        	String email = request.getParameter("email");
	        	Account account = accountService.getAccountByEmail(email);
	        	request.setAttribute("account", account);
			}   
	      
			RequestDispatcher view = request.getRequestDispatcher(forward);
		    view.forward(request, response);
		} catch (BusinessException e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				String action = request.getParameter("action");
				if(action.equalsIgnoreCase("Submit")) {
					String email = request.getParameter("email");
					String name = request.getParameter("name");
					String phone = request.getParameter("phone");
					String address = request.getParameter("address");
					
					Account account = new Account();			
					account.setEmail(email);
					account.setName(name);
					account.setPhone(phone);
					account.setAddress(address);
					
					HttpSession session = request.getSession(true);
					if(String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin")) {
						String role = request.getParameter("role");
						account.setRole(role);
					}
					
					accountService.updateAccount(account);
						response.setContentType("text/html");
					      PrintWriter pw = response.getWriter();
					      pw.println("<script>");
					      pw.println("alert('The account is updated');");
					      pw.println("window.location.href='/mystore/index.jsp';");
					      pw.println("</script>");			
				}	
		} catch (BusinessException e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}
	}
	
}
