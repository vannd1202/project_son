package menu;

import dao.CartDAO;
import dao.ProductDAO;
import entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    ProductDAO productDAO = new ProductDAO();
    Scanner scanner = new Scanner(System.in);
    public void menuManage() throws SQLException {
        while (true) {
            System.out.println("========== CUSTOMER MENU ==========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Chi tiết sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm");
            System.out.println("4. Thêm sản phẩm vào giỏ hàng");
            System.out.println("5. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("6. Cập nhật số lượng trong giỏ hàng");
            System.out.println("7. Xem giỏ hàng");
            System.out.println("8. Đặt hàng");
            System.out.println("9. Xem đơn hàng");
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
                    System.out.print("Nhập ID sản phẩm cần xem: ");
                    int productID = scanner.nextInt();
                    Product product = productDAO.getProductById(productID);
                    if(product != null) {
                        System.out.println("Chi tiết sản phẩm:");
                        System.out.println(product);
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;
                case 3:
                    System.out.println("Nhập tên sản phẩm muốn tìm: ");
                    scanner.nextLine();
                    String productName = scanner.nextLine();
                    List<Product> result = productDAO.searchProducts(productName);
                    if (result.isEmpty()) {
                        System.out.println("Không tìm thấy sản phẩm!");
                    } else {
                        System.out.println("Kết quả: ");
                        for (Product product1 : result) {
                            System.out.println(product1);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Nhập ID sản phẩm: ");
                    int id = scanner.nextInt();

                    System.out.println("Nhập số lượng: ");
                    int so_luong = scanner.nextInt();
                    if (so_luong <= 0) {
                        System.out.println("Số lượng phải > 0");
                        break;
                    }
                    break;
                case 5:
                    System.out.println("Nhập ID sản phẩm cần xóa: ");
                    int id_remove = scanner.nextInt();
                    break;
                case 6:
                    System.out.println("Nhập ID sản phẩm: ");
                    int id_update = scanner.nextInt();

                    System.out.println("Nhập số lượng mới: ");
                    int so_luong_update = scanner.nextInt();
                    if (so_luong_update <= 0) {
                        System.out.println("Số lượng phải > 0");
                        break;
                    }
                    break;
                case 7:
                    System.out.println("Giỏ hàng");
                    break;
                case 8:
                    System.out.println("Đặt hàng");
                    break;
                case 9:
                    System.out.println("Xem đơn hàng");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
