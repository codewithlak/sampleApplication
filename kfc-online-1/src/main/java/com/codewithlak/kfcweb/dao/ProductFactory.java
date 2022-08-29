package com.codewithlak.kfcweb.dao;

public class ProductFactory {
	
	public static ProductManager getProductManagerInstance() {
		
		return new ProductManagerImpl();
	}
}
