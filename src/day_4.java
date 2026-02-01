import java.util.Scanner;

/*
//BÀI 1: Cho người nhập vào số lượng màu bằng tiếng việt
kết quả in ra là màu bằng tiếng anh
 */
public class day_4 {
    public static void main(String[] args) {
        boolean exist = true;
        Scanner scanner = new Scanner(System.in);
        while (exist) {
            System.out.println("bạn muốn nhập bao nhiêu màu");
            int so_luong = Integer.parseInt(scanner.nextLine());
            System.out.println("bạn đã nhập vào " + so_luong + " màu");
            String mang_mau[] = new String[so_luong];
            for (int i = 0; i < mang_mau.length; i++) {
                System.out.println("nhập mau thứ " + (i + 1));
                String color = scanner.next();
                mang_mau[i] = color;
            }
            for (int i = 0; i < mang_mau.length; i++) {
                if(mang_mau[i].equals("đỏ")){
                    System.out.println("red");
                } else if (mang_mau[i].equals("xanh la")) {
                    System.out.println("green");
                } else if (mang_mau[i].equals("xanh duong")) {
                    System.out.println("blue");
                } else if (mang_mau[i].equals("vàng")) {
                    System.out.println("yellow");
                } else if (mang_mau[i].equals("đen")) {
                    System.out.println("black");
                }
            }

            System.out.println("ban có muốn thoát không (y/n)");
            scanner.nextLine();
            String exit = scanner.nextLine();
            if (!exit.equals("y")) {
                exist = false;
            }
        }

    }
}
