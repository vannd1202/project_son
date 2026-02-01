//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Cách 1
        String[] arr_start = new String[7];
        arr_start[0] = "*";
        arr_start[1] = "**";
        arr_start[2] = "***";
        arr_start[3] = "****";
        arr_start[4] = "*****";
        arr_start[5] = "******";
        arr_start[6] = "*******";

        for (int i = 0; i < arr_start.length; i++){
            System.out.println(arr_start[i]);
        }

        for (int i = arr_start.length - 1; i >= 0; i--) {
            System.out.println(arr_start[i]);
        }

        //Cách 2
        for (int i = 0; i < 7; i++) {
            StringBuilder start = new StringBuilder();
            for (int j = 0; j < i; j++) {
                start.append("*");
            }
            System.out.println(start);
        }
    }
}