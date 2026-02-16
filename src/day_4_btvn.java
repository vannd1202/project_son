import java.util.Scanner;
/*
//bài 1 : ["apple", "banana", "orange", "strawberry"]
-Tìm và in ra chuỗi dài nhất
-In ra độ dài của chuỗi đó
//bài 2: cho chuỗi ""java spring java react java""
-Đếm xem từ "java" xuất hiện bao nhiêu lần
//bài 3: ["java", "spring", "java", "react", "spring"]
Cho k = 4
Đếm số chuỗi có độ dài lớn hơn k
//bài 4:["java", "spring", "c", "react"]
tìm chuỗi ngắn nhất
 */
public class day_4_btvn {
    public static void main(String[] args) {
        /*

        //BÀI 1
        String[] fruits = new String[] {"apple", "banana", "orange", "strawberry"};
        int max = 0;
        String longest = fruits[0];
        for (int i = 0; i < fruits.length; i++) {
            if (max < fruits[i].length()) {
                max = fruits[i].length();
            }
        }
        for (int i = 0; i < fruits.length; i++) {
            if (longest.length() < fruits[i].length()) {
                longest = fruits[i];
            }
        }
        System.out.println("Chuỗi dài nhất có độ dài là: " + max);
        System.out.println("Chuỗi dài nhất là: " + longest);

         */

        /*

        //BÀI 4
        String[] languge = new String[] {"java", "spring", "c", "react"};
        int min = languge[0].length();
        String shortest = languge[0];
        for (int i = 0; i < languge.length; i++) {
            if (min > languge[i].length()) {
                min = languge[i].length();
            }
        }
        for (int i = 0; i < languge.length; i++) {
            if (shortest.length() > languge[i].length()) {
                shortest = languge[i];
            }
        }
        System.out.println("Chuỗi ngắn nhất có độ dài là: " + min);
        System.out.println("Chuỗi ngắn nhất là: " + shortest);

         */

        /*

        //BÀI 2
        String[] languge = new String[]{"java", "spring", "java", "react", "java"};
        int count = 0;
        boolean check = true;
        Scanner scanner = new Scanner(System.in);
        while (check) {
            System.out.println("Đang kiểm tra số lần xuất hiện chữ 'java'.....");
            for (int i = 0; i < languge.length; i++) {
                if (languge[i].equals("java")) {
                    count++;
                }
            }
            System.out.println("Chữ java xuất hiện " + count + " lần");
            System.out.println("ban có muốn thoát không (y/n)");
            String exit = scanner.nextLine();
            if (exit.equals("y")) {
                check = false;
            }
        }

         */

        //BÀI 3
        String[] languge = new String[]{"java", "spring", "java", "react", "spring"};
        int k = 4;
        int count =0;
        boolean check = true;
        while (check) {
            System.out.println("Chuỗi có độ dài lớn hơn 4 là: ");
            for (int i = 0; i <= k; i++) {
                if (languge[i].length() <= k) {
                    check = false;
                }
                if (languge[i].length() > k) {
                    count++;
                    System.out.println(languge[i]);
                }
            }
        }
    }
}
