import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateAPITest_01 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        int dayOfYear = today.getDayOfYear();
        System.out.println("오늘은 1년 중 " + dayOfYear);

        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println("오늘의 요일 : " + dayOfWeek);

        Calendar calendar = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();

    }
}
