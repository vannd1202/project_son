//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        String[] a = new String[7];
//        a [0] = "*";
//        a [1] = "**";
//        a [2] = "***";
//        a [3] = "****";
//        a [4] = "*****";
//        a [5] = "******";
//        a [6] = "*******";
//
//        for (int i = 0; i < 7; i++){
//            System.out.println(a[i]);
//        }


        for (int i = 1; i < 7; i++){
            StringBuilder start = new StringBuilder();
            for(int j = 0; j < i; j++){
                start.append("*");
            }
            System.out.println(start);
        }

    }
}