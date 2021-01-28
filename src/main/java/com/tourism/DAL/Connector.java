package com.tourism.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public abstract class Connector {
  private static final Logger logger = Logger.getLogger(Connector.class.getName());
  protected String host;
  protected String userName;
  protected String password;
  protected String database;
  protected Connection connection = null;

  public Connector(String host, String userName, String password, String database) {
    this.host = host;
    this.userName = userName;
    this.password = password;
    this.database = database;
  }

  public abstract void getConnect();

  public abstract ResultSet executeQuery(String query);

  public abstract int executeUpdate(String query);
}
