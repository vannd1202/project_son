package menu;

import dao.ProductDAO;
import entity.Product;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    ProductDAO productDAO = new ProductDAO();
    Scanner scanner = new Scanner(System.in);
    public void menuManage(){
        while (true) {
            System.out.println("========== ADMIN MENU ==========");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Sửa thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Xem danh sách khách hàng");
            System.out.println("6. Xem tất cả đơn hàng");
            System.out.println("7. Cập nhật trạng thái đơn hàng");
            System.out.println("0. Quay lại");

            System.out.println("Mời nhập lựa chọn: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    List<Product> products = productDAO.getAllProducts();
                    System.out.println("Danh sách sản phẩm hiện có: ");
                    for(Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Thêm sản phẩm");
                    break;
                case 3:
                    System.out.println("Sửa thông tin sản phẩm");
                    break;
                case 4:
                    System.out.println("Xóa sản phẩm");
                    break;
                case 5:
                    System.out.println("Xem danh sách khách hàng");
                    break;
                case 6:
                    System.out.println("Xem tất cả đơn hàng");
                    break;
                case 7:
                    System.out.println("Cập nhật trạng thái đơn hàng");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
