/*

Viết chương trình gồm các chức năng:
//Menu:
1. Thêm sản phẩm
- Nhập tên sản phẩm
- Nhập giá sản phẩm
- Lưu vào <List> theo dạng <Tên-Giá>
2. Hiển thị danh sách sản phẩm
- In toàn bộ sản phẩm trong danh sách
3. Tìm sản phẩm theo tên
- Nhập tên cần tìm
- Hiển thị các sản phẩm có tên trùng
4. Lọc sản phẩm có giá > 100
- Hiển thị các sản phẩm có giá > 100
5. Xóa sản phẩm theo tên
6. Sắp xếp sản phẩm theo tên A -> Z
7. Thoát chương trình

//Sản phẩm:
Laptop-1500
Mouse-20
Keyboard-50
Monitor-200

*/

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Bài Làm
public class day_6_btvn {
    //Danh sách sản phẩm
    List<String> products = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        day_6_btvn main = new day_6_btvn();
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
        //Nhập tên
        System.out.println("Nhập tên sản phẩm: ");
        String product = scanner.nextLine();
        //Nhập giá
        System.out.println("Nhập giá sản phẩm: ");
        int price = Integer.parseInt(scanner.nextLine());
        //Kết quả
        products.add(product + "-" + price);
        System.out.println("Thêm sản phẩm thành công!");
    }

    //Hiển thị danh sách
    public void displayProduct(){
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm hiện đang trống!");
            return;
        }
        System.out.println("Danh sách sản phẩm: ");
        for (String product : products) {
            System.out.println(product);
        }
    }

    //Tìm kiếm sản phẩm
    public void searchProduct(){
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        for (String product : products) {
            if (product.contains(name)) {
                System.out.println("Đã tìm thấy: " + product);
                return;
            }
        }
        System.out.println("-----Không tìm thấy-----");
    }

    //Lọc sản phẩm theo giá
    public void filterProduct(){
        System.out.println("Danh sách sản phẩm thỏa mãn: ");
    }

    //Xóa sản phẩm
    public void removeProduct(){
        System.out.println("Nhập tên sản phẩm cần xóa: ");
        String name = scanner.nextLine();
        if (products.remove(name)) {
            System.out.println("Đã xóa thành công!");
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    //Sắp xếp theo tên
    public void sortProduct(){
        Collections.sort(products);
        System.out.println("Danh sách sản phẩm sau khi sắp xếp: ");
        for (String product : products) {
            System.out.println(product);
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
