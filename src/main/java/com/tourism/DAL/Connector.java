package com.tourism.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Connector {
  private static final Logger logger = Logger.getLogger(Connector.class.getName());
  protected String host;
  protected String userName;
  protected String password;
  protected String database;
  protected Connection connection = null;
  protected Statement statement = null;
  protected ResultSet resultSet = null;

  public Connector(String host, String userName, String password, String database) {
    super();
    this.host = host;
    this.userName = userName;
    this.password = password;
    this.database = database;
  }

  public abstract void getConnect();

}
