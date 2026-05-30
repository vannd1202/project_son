import java.util.ArrayList;
import java.util.List;

public class day_5 {
    public static void main(String[] args) {
/*        int a = 5;
        System.out.println(a++);
        System.out.println(a);
        System.out.println(++a);
        int a = 10;
        int b = a;
        b = 20;
        System.out.println(a); // 10*/

/*        List<Integer> list_number = new ArrayList<>();
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);
        list_number.add(1);

        int sum = 0;
        for (int i = 0; i < list_number.size(); i++) {
            sum += list_number.get(i);
        }
        System.out.println(sum);*/

/*        List<Integer> list_number = new ArrayList<>();
        list_number.add(5);
        list_number.add(8);
        list_number.add(10);
        list_number.add(30);

        list_number.sort((a, b)-> b.compareTo(a));
        for (int i: list_number){
            System.out.println(i);
        }*/

        //Tạo 1 list với tất cả vòng lặp
/*        List<Integer> list_number = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list_number.add(i);
        }

        //for
        for (int i = 0; i < list_number.size(); i++) {
            System.out.println(list_number.get(i));
        }

        //foreach
        System.out.println("------------------------");
        for (int i : list_number) {
            System.out.println(i);
        }

        //while
        System.out.println("------------------------");
        int soLuong1 = 0;
        while (soLuong1 < list_number.size()) {
            System.out.println(list_number.get(soLuong1));
            soLuong1++;
        }

        //do while
        System.out.println("------------------------");
        int soLuong2 = 0;
        do {
            System.out.println(list_number.get(soLuong2));
            soLuong2++;
        } while (soLuong2 < list_number.size());
        */

        //cho một List<String> gồm 5 tên.
        List<String> names = new ArrayList<>();
        names.add("AA");
        names.add("BBBBBBB");
        names.add("CCCC");
        names.add("DDDDD");
        names.add("EEEEEE");

        //In ra tên dài nhất
        int max = 0;
        String maxname = " ";
        for (int i = 0; i < names.size(); i++) {
            if (max < names.get(i).length()) {
                max = names.get(i).length();
                maxname = names.get(i);
            }
        }
        System.out.println("Tên dài nhất có độ dài là: " + max);
        System.out.println("Tên dài nhất là: " + maxname);

        //In ra tên có độ dài > 5
        System.out.println("-----------------------------------");
        System.out.println("Tên có kí tự lớn hơn 5 là: ");
        for (String name : names) {
            if (name.length() > 5) {
                System.out.println(name);
            }
        }
    }
}
