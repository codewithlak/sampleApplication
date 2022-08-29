package com.codewithlak.kfcweb.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codewithlak.kfcweb.dao.ProductFactory;
import com.codewithlak.kfcweb.dao.ProductManager;
import com.codewithlak.kfcweb.model.Product;

public class ProductService {

	private static ProductService productServiceObj;
	
	private ProductService() {
		
	}

	public static synchronized ProductService getProductServiceInstance() {
		
		if(productServiceObj == null) {
			productServiceObj = new ProductService();
		}
		
		return productServiceObj;
	}	
	
	//-----------------------------services	
	public boolean addProduct(Product product) throws ClassNotFoundException, SQLException {
		
		ProductManager productManager = ProductFactory.getProductManagerInstance();
		
		return productManager.addProduct(product);
	}
	
	public Product getProduct(int productCode) throws ClassNotFoundException, SQLException {
		
		ProductManager productManager = ProductFactory.getProductManagerInstance();
			
		return productManager.getProduct(productCode);
	}
	
	public List<Product> getProducts() throws ClassNotFoundException, SQLException {
		
		ProductManager productManager = ProductFactory.getProductManagerInstance();
		
		return productManager.getProducts();
	}
	
	public boolean updateProduct(Product product) throws ClassNotFoundException, SQLException {
		
		ProductManager productManager = ProductFactory.getProductManagerInstance();
		
		return productManager.updateProduct(product);
	}
	
	public boolean deleteProduct(int productCode) throws ClassNotFoundException, SQLException {
		
		ProductManager productManager = ProductFactory.getProductManagerInstance();
		
		return productManager.deleteProduct(productCode);
	}
}
