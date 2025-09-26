import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DateAPITest_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year, month, day;

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1998, 9, 19);


        System.out.println(today.compareTo(birthday));


        // until함수
        System.out.println(birthday.until(today, ChronoUnit.YEARS));
        System.out.println(birthday.until(today, ChronoUnit.MONTHS));
        System.out.println(birthday.until(today, ChronoUnit.DAYS));

    }
}
