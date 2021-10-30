package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

  final static String username = "root";
  final static String password = "";

  public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost/quan_ly_thu_vien";
    return DriverManager.getConnection(url, username, password);
  }
}
