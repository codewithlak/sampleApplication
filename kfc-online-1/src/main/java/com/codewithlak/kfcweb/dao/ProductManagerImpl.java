package com.codewithlak.kfcweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codewithlak.kfcweb.model.Product;

public class ProductManagerImpl implements ProductManager{

	/*
	 * 1. Import the package
	 * 2. Load the driver
	 * 3. Establish the connection
	 * 4. Create the statement
	 * 5. Execute the query
	 * 6. Process the result
	 * 7. Close the connection and the statement  
	 */
		
	private Connection getconnection() throws ClassNotFoundException, SQLException {
		
		DbConnector mysql = new MySqlDbConnectorImpl();
		return mysql.getConnection();
	}
	
	public boolean addProduct(Product product) throws ClassNotFoundException, SQLException {
		
		Connection connection = getconnection();
		//Statement st = connection.createStatement();
		//String query = "INSERT INTO product (productName, price) VALUES(" + product.getProductName() + "," + product.getPrice() + ")";
		
		//int result = st.executeUpdate(query);
		//st.close();
		
		String query = "INSERT INTO product (productName, price) VALUES(?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, product.getProductName());
		ps.setDouble(2, product.getPrice());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result > 0;
	}

	public Product getProduct(int productCode) throws ClassNotFoundException, SQLException {

		Connection connection = getconnection();
		
		String query = "SELECT * FROM product WHERE productId = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, productCode);
		
		ResultSet rs = ps.executeQuery();
		
		Product product = new Product();
		
		while(rs.next()) {
			product.setProductCode(rs.getInt("productId"));
			product.setProductName(rs.getString("productName"));
			product.setPrice(rs.getDouble("price"));
		}
		
		ps.close();
		connection.close();
		return product;
	}

	public List<Product> getProducts() throws ClassNotFoundException, SQLException {

		Connection connection = getconnection();
		String query = "SELECT * FROM product";
		
		Statement st = connection.createStatement(); 
		ResultSet rs = st.executeQuery(query);
		
		List<Product> productList = new ArrayList();
		
		while(rs.next()) {
			Product product = new Product();
			product.setProductCode(rs.getInt("productId"));
			product.setProductName(rs.getString("productName"));
			product.setPrice(rs.getDouble("price"));
			
			productList.add(product);
		}
		
		st.close();
		connection.close();
		
		return productList;
	}

	public boolean updateProduct(Product product) throws ClassNotFoundException, SQLException {

		Connection connection = getconnection();
		String query = "UPDATE product SET productName = ?, price = ? WHERE productId = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, product.getProductName());
		ps.setDouble(2, product.getPrice()); 
		ps.setInt(3, product.getProductCode());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result > 0;
	}

	public boolean deleteProduct(int productCode) throws ClassNotFoundException, SQLException {

		Connection connection = getconnection();
		String query = "DELETE FROM product WHERE productId = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, productCode);
		
		int result = ps.executeUpdate();
		ps.close();
		connection.close();
		
		return result > 0;
	}
	
}
