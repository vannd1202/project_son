/*
//BÀI 1
in ra những số thuộc vào number1 nhưng ko thuộc number2
giả sử với mảng 1 là những số hoàn toàn ko thuộc vào mảng 2 (chưa hiểu đoạn này)
 */

public class day_3 {
    public static void main(String[] args) {
        int[] arr_number1 = new int[]{5, 15, 16, 17, 18, 19, 20};
        int[] arr_number2 = new int[]{1, 3, 5, 15, 16, 17, 18, 19, 20};

//        for (int i = 0; i < arr_number1.length; i++) {
//            int j;
//            for (j = 0; j < arr_number2.length; j++) {
//                if (arr_number1[i] == arr_number2[j]) {
//                    break;
//                }
//            }
//            if (j == arr_number2.length) {
//                System.out.println(arr_number1[i] + " ");
//            }
//        }

        for (int i = 0; i < arr_number1.length; i++) {
            int check_exist = 0;
            for (int j = 0; j < arr_number2.length; j++) {
                if (arr_number1[i] == arr_number2[j]) {
                    check_exist = 1;
                    break;
                }
            }
            if (check_exist == 0) {
                System.out.println(arr_number1[i] + " ");
            }
        }

    }
}
