import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int so_sao = 1;
        String[] arr_start = new String [7];
        arr_start [0] = "*";
        arr_start [1] = "**";
        arr_start [2] = "***";
        arr_start [3] = "****";
        arr_start [4] = "*****";
        arr_start [5] = "******";
        arr_start [6] = "*******";

        for (int i = 0 ; i < 7  ; i++) {
            System.out.println(arr_start[i]);
        }

         for (int i = 6 ; i >= 0 ; i--) {
            System.out.println(arr_start[i]);
        }
        String a = "hello";
        String b = "world";
        String c = a + b;
        String d = "các bạn";
        String text = c +d;
        System.out.println(text);

        for (int i = 1 ; i < 7  ; i++) {
            StringBuilder start = new StringBuilder();
            for (int j = 0 ; j < i ; j++) {
                start.append("*");
            }
            System.out.println(start);

        }






    }
}