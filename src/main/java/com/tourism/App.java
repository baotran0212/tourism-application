package com.tourism;

import com.tourism.DAL.Connector;
import com.tourism.DAL.MysqlConnector;
import com.tourism.service.JsonAddress;

public class App {

  public static void main(String[] args) {
    // Connector conn = new MysqlConnector("localhost", "root", "123", "todo");
    // conn.getConnect();
	 JsonAddress j = new JsonAddress();
	  //System.out.println(j.getAddressLv2("Thành phố Hồ Chí Minh"));
	 // System.out.println(j.getAddressLV1());
	// System.out.println(j.getAddressLV3("Thành phố Hồ Chí Minh", "Quận 12"));
	  
  }

}
