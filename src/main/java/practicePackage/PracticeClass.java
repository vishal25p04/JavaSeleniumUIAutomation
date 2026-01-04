package practicePackage;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PracticeClass {
    public static void main (String args []){
        System.out.println("Hello World");
//        Date date = new Date();
//        System.out.println("Current date and time: " + date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String currentDateTime = formatter.format(java.time.LocalDateTime.now());

        System.out.println("***"+formatter.format(java.time.LocalDateTime.now()));
        System.out.println("***"+formatter.format(java.time.LocalDateTime.now()));
        System.out.println("***"+formatter.format(java.time.LocalDateTime.now()));

    }
}
