package menu;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    public void menuManage() throws SQLException {
        while(true){
            System.out.println("====== MENU ======");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("0. Exit");

            System.out.println("Vui lòng nhập vai trò:");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.menuManage();
                    break;
                case 2:
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.menuManage();
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại lần sau!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
