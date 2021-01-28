package com.tourism.DAL;

import java.sql.*;
import java.util.logging.Logger;

import com.tourism.ProjectProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
public class MysqlConnector extends Connector {
  private static Logger logger = Logger.getLogger(MysqlConnector.class.getName());

  public MysqlConnector() {
	super();
    this.host = ProjectProperties.getProperties("mysql.host");
    this.userName = ProjectProperties.getProperties("mysql.user");
    this.password = ProjectProperties.getProperties("mysql.password");
    this.database = ProjectProperties.getProperties("mysql.database");
    System.out.println(super.toString());
    this.getConnect();
  }

  public void getConnect() {
    String url = "jdbc:mysql://" + this.host + ":3306/" + this.database;
    System.out.println(url);
    try {
      this.connection = DriverManager.getConnection(url, this.userName, this.password);
      logger.info("Connect success!!!");
    } catch (Exception e) {
      logger.info(e.toString());
    }
  }

  public int executeUpdate(String query) {
    int res = Integer.MIN_VALUE;
    try {
      res = this.connection.createStatement().executeUpdate(query);
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      this.closeConnection();
    }
    return res;
  }

  public ResultSet executeQuery(String query) {
    ResultSet rs = null;
    try {
      rs = this.connection.createStatement().executeQuery(query);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return rs;
  }

  public void closeConnection() {
    try {
      if (this.connection != null && !this.connection.isClosed()) {
        this.connection.close();
        this.connection = null;
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public static void main(String[] args) {
    Connector connector = new MysqlConnector();
  }
}
