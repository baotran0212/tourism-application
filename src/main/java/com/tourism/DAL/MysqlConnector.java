package com.tourism.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class MysqlConnector extends Connector{
	private static final Logger logger = Logger.getLogger(Connector.class.getName());
	private Connection connection;
	
	public MysqlConnector(String host, String userName, String password, String database) {
		super(host, userName, password, database);
	}

	@Override
	public void getConnect() {
		if (this.connection == null) {
			// driverTest
			String url = "jdbc:mysql://" + this.host + ":3306/" + this.database;
			try {
				this.connection = DriverManager.getConnection(url, this.userName, this.password);
				logger.info("Connect success!!!");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
