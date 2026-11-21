package org.example.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy MySQL Driver", e);
        }

        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh";
        String user = "root";
        String pass = "12345678"; // Thay bằng mật khẩu MySQL trên máy của bạn

        return DriverManager.getConnection(url, user, pass);
    }
}
