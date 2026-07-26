package main;

import menu.MainMenu;
import utils.DBConnection;

public class Main {
    public static void main(String[] args) {
        //Kiểm tra kết nối tới DB_Sales
        DBConnection.getConnection();
        //Xem danh sách sản phẩm
        MainMenu menu = new MainMenu();
        menu.menuManage();
    }
}