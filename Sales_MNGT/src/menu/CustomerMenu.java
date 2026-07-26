package menu;

import dao.ProductDAO;
import entity.Product;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    ProductDAO productDAO = new ProductDAO();
    Scanner scanner = new Scanner(System.in);
    public void menuManage(){
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
                    System.out.println("Tìm kiếm sản phẩm");
                    break;
                case 4:
                    System.out.println("Thêm sản phẩm vào giỏ hàng");
                    break;
                case 5:
                    System.out.println("Xóa sản phẩm khỏi giỏ hàng");
                    break;
                case 6:
                    System.out.println("Cập nhật số lượng");
                    break;
                case 7:
                    System.out.println("Xem giỏ hàng");
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
