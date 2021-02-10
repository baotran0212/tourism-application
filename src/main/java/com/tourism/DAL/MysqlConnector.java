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
  public static int count = 0; 

  public MysqlConnector() {
	super();
    this.host = ProjectProperties.getProperties("mysql.host");
    this.userName = ProjectProperties.getProperties("mysql.user");
    this.password = ProjectProperties.getProperties("mysql.password");
    this.database = ProjectProperties.getProperties("mysql.database");
    //this.getConnect();
  }

  public void getConnect() {
    if(connection == null) {
    	String url = "jdbc:mysql://" + this.host + ":3306/" + this.database;
        try {
          this.connection = DriverManager.getConnection(url, this.userName, this.password);
          logger.info("Connect success!!! count = " + count++);
        } catch (Exception e) {
          logger.info(e.toString());
        }
    }
  }
 
	@Override
	public void setStatement() {
		try {
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  public int executeUpdate(String query) {
    int res = Integer.MIN_VALUE;
    try {
    	this.getConnect();
    	this.setStatement();
    	this.statement.execute(query);
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
    	this.getConnect();
    	this.setStatement();
    	rs = this.statement.executeQuery(query);
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
    }
    return rs;
  }

  public void closeConnection() {
    try {
      if (this.connection != null && !this.connection.isClosed()) {
        this.connection.close();
        this.connection = null;
      }
      
//      if(this.statement != null && !this.statement.isClosed()) {
//    	  this.statement.close();
//    	  this.statement.close();
//      }
//      
//      if(this.resultSet!=null && !this.resultSet.isClosed()) {
//    	  this.resultSet.close();
//    	  this.resultSet.close();
//      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public static void main(String[] args) {
    Connector connector = new MysqlConnector();
  }
}
