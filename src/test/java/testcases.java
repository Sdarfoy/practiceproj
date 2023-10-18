
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class testcases {
    public static void main(String[] args) {
        String string = "номер: один " + "\n" + "Пользователь: юзер";
        String[] list = string.split("\n|\\: ");
        System.out.println(list[1]);

    }
    public static Date parseDate (String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}

