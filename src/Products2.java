/*
Day 8 (2)
Nội dung bài tập ở class BaseObject
 */

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;

public class Products2 extends BaseObject {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    List <Products2> products = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Products2 main = new Products2();
        boolean exist = true;
        do {
            main.menuManage();
            String choice = main.inputData();
            switch (choice) {
                case "1":
                    main.addProduct();
                    break;
                case "2":
                    main.displayProduct();
                    break;
                case "3":
                    main.searchProduct();
                    break;
                case "4":
                    main.filterProduct();
                    break;
                case "5":
                    main.removeProduct();
                    break;
                case "6":
                    main.sortProduct();
                    break;
                case "7":
                    System.out.println("Hẹn gặp lại lần sau");
                    exist = false;
                    break;
            }
        } while (exist);
    }
    //Thêm sản phẩm
    public void addProduct(){
        Products2 product = new Products2();
        //Nhập tên sản phẩm
        System.out.println("Vui lòng nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        product.setName(name);
        //Nhập giá sản phẩm
        System.out.println("Vui lòng nhập giá sản phẩm: ");
        try {
            int price = Integer.parseInt(scanner.nextLine());
            product.setPrice(price);
        } catch (NumberFormatException e) {
            System.out.println("-----Lỗi Exception-----");
        }
        //Kết quả
        products.add(product);
        System.out.println("Thêm sản phẩm thành công!");
    }

    //Hiển thị danh sách
    public void displayProduct(){
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm hiện đang trống!");
            return;
        }
        System.out.println("Danh sách sản phẩm: ");
        for (Products2 list : products) {
            System.out.println(list.getName() + " - " + list.getPrice());
        }
        System.out.println("------------------------------------");
    }

    //Tìm kiếm sản phẩm
    public void searchProduct(){
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        for (Products2 product : products) {
            if (product.getName().contains(name)) {
                System.out.println("Đã tìm thấy: " + product.getName() + " - " + product.getPrice());
//                return;
            }
        }
        System.out.println("-----Không tìm thấy-----");
    }

    //Lọc sản phẩm theo giá
    public void filterProduct(){
        System.out.println("Danh sách sản phẩm thỏa mãn điều kiện: ");
        for (Products2 product : products) {
            if (product.getPrice() > 100) {
                System.out.println(product.getName() + " - " + product.getPrice());
                return;
            }
        }
    }

    //Xóa sản phẩm
    public void removeProduct(){
        try {
            System.out.println("Nhập tên sản phẩm cần xóa: ");
            String name = scanner.nextLine();
            for (Products2 product : products) {
                if (product.getName().contains(name)) {
                    products.remove(product);
                    System.out.println("Đã xóa thành công!");
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("-----Lỗi Exception-----");
        }
        System.out.println("Không tìm thấy sản phẩm");
    }

    //Sắp xếp theo tên
    public void sortProduct(){
        for (Products2 product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }

    //Danh sách chức năng
    public void menuManage(){
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Hiển thị danh sách sản phẩm");
        System.out.println("3. Tìm kiếm sản phẩm");
        System.out.println("4. Lọc sản phẩm");
        System.out.println("5. Xóa sản phẩm");
        System.out.println("6. Sắp xếp danh sách sản phẩm");
        System.out.println("7. Thoát");
    }

    //Nhập lựa chọn từ bàn phím
    public String inputData(){
        System.out.println("Nhập lựa chọn của bạn: ");
        return scanner.nextLine();
    }
}
