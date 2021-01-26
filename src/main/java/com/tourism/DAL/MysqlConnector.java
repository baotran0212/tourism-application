package com.tourism.DAL;

import java.sql.*;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class MysqlConnector extends Connector {
  private static Logger logger = Logger.getLogger(MysqlConnector.class.getName());

  public void getConnect() {
    // driverTest
    String url = "jdbc:mysql://" + this.host + ":3306/" + this.database;
    try {
      this.connection = DriverManager.getConnection(url, this.userName, this.password);
      logger.info("Connect success!!!");
    } catch (Exception e) {
      logger.info(e.toString());
    }
  }

  public void getStatement() {
    try {
      if (this.statement == null ? true : this.statement.isClosed()) {
        this.statement = this.connection.createStatement();
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

  }

}
