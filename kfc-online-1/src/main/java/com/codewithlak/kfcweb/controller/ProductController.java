package com.codewithlak.kfcweb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codewithlak.kfcweb.model.Product;
import com.codewithlak.kfcweb.service.ProductService;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service;
	private String message;
	private String deleteMessage;
		
	public ProductController() {
		service = ProductService.getProductServiceInstance();
	}
	       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	var productCode = request.getParameter("productcode");
    	if(productCode != null) {    		

    		launchSpecificProductInformation(request, response);
    	}
    	else {  

    		launchAllProducts(request, response);
    	}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var type= request.getParameter("type");
		if(type != null) {
			if(type.equals("update")) {
				updateProduct(request, response);
			} else if(type.equals("delete")) {
				deleteProduct(request, response);
			} else {
				insertProduct(request, response);
			}
		}
	}
		
	private void launchSpecificProductInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Product product;
		clearMessage();
		
		try {
			product = service.getProduct(Integer.parseInt(request.getParameter("productcode")));
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			
			message = e.getMessage();
			product = new Product();
		}
		
		HttpSession session = request.getSession();		
		session.setAttribute("message", message);
		session.setAttribute("product", product);
		System.out.println(product.getProductName());
		response.sendRedirect("manage-product.jsp");
	}
		
	private void launchAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	List<Product> productList;
    	clearMessage();
    	
		try {
			productList = service.getProducts();
		} catch (ClassNotFoundException | SQLException e) {
			
			message = e.getMessage();
			productList = new ArrayList<Product>();
		}
		request.setAttribute("productList", productList);
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("welcome-to-kfc.jsp");		
		rd.forward(request, response);
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productCode = Integer.parseInt(request.getParameter("productcode"));
		String productName = request.getParameter("productname");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Product product = new Product(productCode, productName, price);
		clearMessage();
		
		try {
			
			boolean result = service.updateProduct(product);
			if(result) {
				message = "Product has been successfully updated! Product Code: " + productCode;
			}
			else {
				message = "Failed to update the product! Product Code: " + productCode;
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("product", product);
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("manage-product.jsp");
		rd.forward(request, response);
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productCode = Integer.parseInt(request.getParameter("productCode"));
		clearMessage();
		try {
			boolean result = service.deleteProduct(productCode);
			if(result) {
				deleteMessage = "The product: " + productCode + " has been successfully deleted!";
			} else {
				deleteMessage = "Failed to delete the product! Product Code: " + productCode;
			}
		} catch (ClassNotFoundException | SQLException e) {
			deleteMessage = e.getMessage();
		}
		request.setAttribute("deleteMessage", deleteMessage);
		launchAllProducts(request, response);
	}
	
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		clearMessage();
		String productName = request.getParameter("productname");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Product product = new Product(productName, price);		
		try {
			boolean result = service.addProduct(product);
			if(result) {
				message = "Product has been successfully added! Product Name: " + productName;
			}else {
				message = "Failed to add product! Product Name: " + productName;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("add-product.jsp");
		rd.forward(request, response);
	}
	
	private void clearMessage() {
		message = "";
		deleteMessage = "";
	}
		
}
