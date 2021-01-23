package com.tourism.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public abstract class Connector {
	private static final Logger logger = Logger.getLogger(Connector.class.getName());
	String host;
	String userName;
	String password;
	String database;

	public Connector(String host, String userName, String password, String database) {
		super();
		this.host = host;
		this.userName = userName;
		this.password = password;
		this.database = database;
	}
	
	public abstract void getConnect();
	
}
