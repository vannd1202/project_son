/*
Day 8 (3)
Nội dung bài tập ở class BaseObject
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        Main main = new Main();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Products2 products2 = new Products2();
//        main.SetIdRandom();
        products2.setCreatedDate(LocalDateTime.now());

        System.out.println(products2.getId());
        System.out.println(products2.getCreatedDate());
        System.out.println(products2.getUpdatedDate());
    }

    //Ngày giờ
    public static String localDateTimeToDateWithSlash(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
    }

    //Set ID random
//    public void SetIdRandom(){
//        Random random = new Random();
//        setId(random.nextInt(10));
//    }
}