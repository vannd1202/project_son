package main;

import menu.MainMenu;
import utils.DBConnection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Kiểm tra kết nối tới DB_Sales
        DBConnection.getConnection();
        //Xem danh sách sản phẩm
        MainMenu menu = new MainMenu();
        menu.menuManage();
    }
}