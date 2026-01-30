public class day_2 {
    public static void main(String[] args){
//        int[] a = new int[4];
//        a[0] = 10;
//        a[1] = 5;
//        a[2] = 20;
//        a[3] = 16;
//
//        int min = a[0];
//        for (int i = 0; i < a.length; i++){
//            if (a[i] < min ){
//                min = a[i];
//            }
//        }
//        System.out.println(min + "la so nho nhat");


        int[] arr_number = new int[] {1,2,3,4,5,6,7,8,9,10};
        int[] arr_number2= new int[] {7,8,9,10,11,12,13,14,15};

//        int max1 = 0;
//        for (int i = 0; i < arr_number.length; i++){
//            if (arr_number[i] > max1){
//                max1 = arr_number[i];
//            }
//        }
//        System.out.println(max1 + "la phan tu lon nhat cua mang 1");
//
//        int max2 = 0;
//        for (int j = 0; j < arr_number2.length; j++){
//            if (arr_number2[j] > max2){
//                max2 = arr_number2[j];
//            }
//        }
//        System.out.println(max2 + "la phan tu lon nhat cua mang 2");


//        if (max1 > max2){
//            System.out.println(max1 + "phan tu lon nhat cua mang 1 lon hon mang 2");
//        }
//        if (max1 < max2){
//            System.out.println(max2 + "phan tu lon nhat cua mang 2 lon hon mang 1");
//        }
//        if (max1 == max2){
//            System.out.println("phan tu lon nhat cua 2 mang bang nhau");
//        }

        int sum1 = 0;
        for (int i = 0; i < arr_number.length; i++){
//          sum = sum + arr_number[i];
            sum1 += arr_number[i];
        }
        System.out.println(sum1 + "la tong cua mang 1");


        int sum2 = 0;
        for (int i = 0; i < arr_number2.length; i++){
//          sum = sum + arr_number[i];
            sum2 += arr_number2[i];
        }
        System.out.println(sum2 + "la tong cua mang 2");
    }

}
