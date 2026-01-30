/*

//bài 1 : khởi tạo 1 mang số nguyên
Đếm xem có bao nhiêu:
Số chẵn
Số lẻ
//bài 2 : khởi tạo 1 mảng số nguyên
In mảng ban đầu
Đảo ngược mảng bằng vòng lặp for
In mảng sau khi đảo
//bài 3 :  khởi tạo 1 mảng
Dùng vòng lặp for để tìm:
Số lớn nhất
Số nhỏ nhất
//bài 4 :  cho 1 mảng gồm các số chẵn và lẻ
-in ra danh sách số chẵn và số lẻ
//bài 5 :cho 1 mảng số . đưa ra số xuất hiện nhiều nhất trong mảng
//bài 6: cho 1 mảng số. đưa ra số xuất hiện ít nhất trong mảng

 */

public class day_2_btvn {
    public static void main(String[] args) {
        /*

        //BÀI 1
        int[] A = new int[]{-7, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        //khai bao
        int dem1=0;
        int dem2=0;

        //so chan
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                System.out.println(A[i] + "la so chan");
                dem1++;
            }
        }
        System.out.println("Vay mang A co" + dem1 + "so chan");

        //so le
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 != 0) {
                System.out.println(A[i] + "la so le");
                dem2++;
            }
        }
        System.out.println("Vay mang A co" + dem2 + "so le");

        */
//--------------------------------------------------------------------------------
        /*

        //BÀI 2
        int[] A = new int[]{-2, 6, 1, 2, 3, 4, 5, -9};
        //in mảng ban đầu
        System.out.println("Mảng A:");
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }
        //đảo ngược mảng và in ra màn hình
        System.out.println("Mang dao nguoc cua mang A là:");
        for (int i = A.length - 1; i >= 0; i--) {
            System.out.println(A[i]);
        }

         */
//---------------------------------------------------------------------------------
        /*

        //BÀI 3
        int[] A = new int[]{-45, -3, -2, -30, -78, 0, 100, 2, 3, 4, 21};
        //khai báo
        int max = A[0];
        int min = A[0];

        //số lớn nhất
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        System.out.println(max + "la so lon nhat");

        //số nhỏ nhất
        for (int i = 0; i < A.length; i++) {
            if (A[i] < min) {
                min = A[i];
            }
        }
        System.out.println(min + "la so nho nhat");

         */
//---------------------------------------------------------------------------------
        /*

        //BÀI 4
        int[] A = new int[]{-4, -2, 5, 7, 8, 10, 3, 22};
        //chan
        System.out.println("Danh sách các số chẵn:");
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                System.out.println(A[i]);
            }
        }
        //le
        System.out.println("Danh sách các số lẻ:");
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 != 0) {
                System.out.println(A[i]);
            }
        }

         */
//---------------------------------------------------------------------------------

        //BÀI 5
        int[] A = new int[]{5, 2, 9, 9, 5, 9, 5};
        //khai báo
        int dem1 = 0;


        //số xuất hiện nhiều nhất
        System.out.println("Số xuất hiện nhiều nhất là: ");
        for (int i = 0; i < A.length; i++) {
            int dem2 = 0;
            for (int j = i; j < A.length; j++) {
                if (A[j] == A[i]) {
                    dem2++;
                }
            }
            if (dem2 > dem1) {
                dem1 = dem2;

            }
            if (dem2 == dem1) {
                System.out.println(A[i]);
            }
        }
        System.out.println("Số lần xuất hiện: " + dem1);

//----------------------------------------------------------------------------------
        /*

        //BÀI 6
        int[] A = new int[]{5, 9, 5, 9, 5, 9, 5};
        //khai báo
        int dem1 = A.length;
        int less = A[0];

        //số xuất hiện ít nhất
        for (int i = 0; i < A.length; i++) {
            int dem2 = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j] == A[i]) {
                    dem2++;
                }
            }
            if (dem2 < dem1) {
                dem1 = dem2;
                less = A[i];
            }
        }
        System.out.println("Số xuất hiện ít nhất là: " + less);
        System.out.println("Số lần xuất hiện: " + dem1);

         */
    }
}
