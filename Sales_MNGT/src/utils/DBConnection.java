package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/DB_Sales";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
            return conn;

        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
            return null;
        }
    }
}