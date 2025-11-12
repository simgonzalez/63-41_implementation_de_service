package ch.heg.cours6341.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static Connection getConnection() throws SQLException {
        String url="jdbc:postgresql://localhost:5432/postgres";
        String login="postgres";
        String pwd="postgres";
        return DriverManager.getConnection(url, login, pwd);
    }
}
