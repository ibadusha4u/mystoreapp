package com.mystore.controller.ordermanagement;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ordermanagement.model.Order;
import com.ordermanagement.model.OrderItem;
import com.ordermanagement.service.OrderService;
import com.ordermanagement.service.OrderServiceImpl;

@WebServlet("/MultipartController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 5 * 5)
public class MultipartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
		
	private static String ERROR = "/error.jsp";

	private OrderService orderService;
	
	public MultipartController() {
		super();
		orderService = new OrderServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO: Change the deprecated method
			DiskFileUpload fu = new DiskFileUpload();
			fu.setSizeMax(1024 * 1024 * 5);
			fu.setSizeThreshold(1024);
			fu.setRepositoryPath(System.getProperty("java.io.tmpdir"));
			List fileList = fu.parseRequest(request);
			InputStream uploadedFileStream = null;
			String fileName = null;

			for (Iterator i = fileList.iterator(); i.hasNext();) {
				FileItem fi = (FileItem) i.next();
				if (fi.isFormField()) {
					String key = fi.getFieldName();
					String val = fi.getString();
					System.out.println("Form parameter " + key + "=" + val);
				} else {
					if (fi.getSize() < 1) {
						throw new Exception("No file was uplaoded");
					}
					fileName = fi.getName();
					uploadedFileStream = fi.getInputStream();
				}
			}

			Workbook workbook = new XSSFWorkbook(uploadedFileStream);
			System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
			workbook.forEach(sheet -> {
				System.out.println("=> " + sheet.getSheetName());
			});
			Sheet sheet = workbook.getSheetAt(0);
			List<Order> orderList = new ArrayList<Order>();
			Order order = new Order();

			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			sheet.forEach(row -> {
				if (row.getRowNum() != 0) {
					int sku = (int) row.getCell(0).getNumericCellValue();
					String name = row.getCell(1).getStringCellValue();
					int quantity = (int) row.getCell(2).getNumericCellValue();
					double unitPrice = (Double) row.getCell(3).getNumericCellValue();

					OrderItem orderItem = new OrderItem();
					orderItem.setProductSku(sku);
					orderItem.setSoldQuantity(quantity);
					orderItem.setUnitPrice(unitPrice);
					orderItemList.add(orderItem);
				}
			});
			order.setOrderItem(orderItemList);
			orderList.add(order);
			orderService.add(orderList);

			System.out.println("File " + fileName + " has uploaded successfully!");
			request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
		
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");			
			pw.println("alert('The file has been uploaded successfully!');");
			pw.println("window.location.href='/mystore/OrderController?action=listOrder';");
			pw.println("</script>");

		} catch (Exception e) {			
			e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}

	}

}