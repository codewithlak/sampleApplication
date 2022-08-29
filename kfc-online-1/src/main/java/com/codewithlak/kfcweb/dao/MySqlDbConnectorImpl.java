package com.codewithlak.kfcweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbConnectorImpl implements DbConnector{

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/kfc";
		String user = "root";
		String password = "1qaz2wsx@";
		Connection connection  =  DriverManager.getConnection(url, user, password);
		return connection;
	}	

}
