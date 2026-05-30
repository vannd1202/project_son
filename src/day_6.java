//1. Cho phép người dùng nhập vào tên các thành phố(Không cho nhập trùng ,Không cho nhập rỗng)
//2. Tìm kiếm theo tên thành phố(tìm kiếm gần đúng)
//3. Sắp xếp tên thành phố theo alpha
//4. Xóa tên thành phố theo tên

//Bài Mẫu
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        boolean exits = true;
        List<String>list_city = new ArrayList<>();
        do {

            String chooice = main.menu_manage();
            switch (chooice){
                case "1":
                    // thêm mơi thành phố
                    String city = main.inputData();
                    list_city.add(city);
                    System.out.println("thêm thành phố thành công");
                    System.out.println("dữ liệu "+list_city.size());
                    break;
                case "2":
                    //tìm kiếm theo tên thành phố(tìm kiếm gần đúng)
                    System.out.println("danh sách thành phố tìm kiếm theo tên gần đúng");
                    String search_city = main.inputData();
                    int ket_qua = 0;
                    for (String item : list_city){
                        if (item.contains(search_city)){
                            ket_qua++;
                            System.out.println("------------------"+item+"----------------------------");
                        }
                    }
                    if (ket_qua == 0){
                        System.out.println("------------------không có tên thành phố thỏa mãn----------------------------");
                    }
                    break;
                case "3":
                    //sắp xếp tên thành pho theo alpha
                    break;
                case "4":
                    // sắp xếp tên thành pho theo alpha
                    break;
                case "5":
                    //thoát
                    exits = false;
                    break;
            }
        }while (exits);

    }

    public String menu_manage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Thêm thành phố");
        System.out.println("2.Tìm kiếm  thành phố");
        System.out.println("3 Sắp xếp thêm thành phố");
        System.out.println("4.Xóa thành phố");
        System.out.println("5.Thoát");
        System.out.println("bạn chọn chức năng nào");
        String choice = scanner.nextLine();
        return choice;
    }


    public String inputData(){
        System.out.println("mời bạn nhập vào tên thành phố");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        return city ;
    }
}
*/

//Bài Làm
import java.util.*;
public class day_6 {
    //Danh sách thành phố
    List<String> cities = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        day_6 main = new day_6();
        boolean exist = true;
        do {
            main.menu_manage();
            String choice = main.inputData();
            switch (choice){
                case "1":
                    main.add_city();
                    break;
                case "2":
                    main.search_city();
                    break;
                case "3":
                    main.sort_city();
                    break;
                case "4":
                    main.remove_city();
                    break;
                case "5":
                    exist = false;
                    break;
            }
        } while (exist);
    }

    //Nhập tên thành phố
    public void add_city(){
        System.out.println("Mời bạn thêm thành phố");
        String city = scanner.nextLine();
        //Không cho nhập rỗng
        if (city.isBlank()){
            System.out.println("-----Không được để trống-----");
            return;
        }
        //Không cho nhập trùng
        for (String dup : cities){
            if (dup.equalsIgnoreCase(city)){
                System.out.println("-----Thành phố đã tồn tại-----");
                return;
            }
        }
        cities.add(city);
        System.out.println("-----Thêm thành phố thành công-----");
    }

    //Tìm kiếm theo tên
    public void search_city(){
        System.out.print("Nhập tên cần tìm: ");
        String name = scanner.nextLine();
        //Tìm kiếm gần đúng
        for (String city : cities) {
            if (city.contains(name)) {
                System.out.println("Đã tìm thấy: " + city);
                return;
            }
        }
        System.out.println("-----Không tìm thấy-----");
    }

    //Sắp xếp thành phố
    public void sort_city(){
        //Sắp xếp tên theo alphabet
        Collections.sort(cities);
        System.out.println("Danh sách sau khi sắp xếp:");
        for (String city : cities) {
            System.out.println(city);
        }
    }

    //Xóa tên thành phố
    public void remove_city(){
        System.out.print("Nhập tên thành phố cần xóa: ");
        String name = scanner.nextLine();
        if (cities.remove(name)) {
            System.out.println("-----Đã xóa thành công-----");
        } else {
            System.out.println("-----Không tìm thấy thành phố-----");
        }
    }

    //Danh sách lựa chọn
    public void menu_manage(){
        System.out.println("1. Thêm thành phố");
        System.out.println("2. Tìm kiếm tên thành phố");
        System.out.println("3. Sắp xếp tên thành phố");
        System.out.println("4. Xóa");
        System.out.println("5. Thoát");
    }

    //Lựa chọn người dùng nhập từ bàn phím
    public String inputData(){
        System.out.println("Nhập lựa chọn của bạn: ");
        return scanner.nextLine();
//        String input = scanner.nextLine();
//        return input;
    }
}
