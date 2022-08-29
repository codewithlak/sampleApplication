package com.codewithlak.kfcweb.dao;

import java.sql.SQLException;
import java.util.List;

import com.codewithlak.kfcweb.model.Product;

public interface ProductManager {
	
	public boolean addProduct(Product product) throws ClassNotFoundException, SQLException;
	public Product getProduct(int productCode) throws ClassNotFoundException, SQLException;
	public List<Product> getProducts() throws ClassNotFoundException, SQLException;
	public boolean updateProduct(Product product) throws ClassNotFoundException, SQLException;
	public boolean deleteProduct(int productCode) throws ClassNotFoundException, SQLException;
}
