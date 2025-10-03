package ch.heg.cours6341.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

  public static Connection getConnection() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/cours6341";
    String login = "postgres";
    String pwd = "password";
    return DriverManager.getConnection(url, login, pwd);
  }
}
