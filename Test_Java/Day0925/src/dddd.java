import java.time.LocalDateTime;

public class dddd {
    public static void main(String[] args) {
        LocalDateTime koreanWar = LocalDateTime.of(1950, 6, 25, 0, 0, 0);
        LocalDateTime independence = LocalDateTime.of(1945, 8, 15, 0, 0);
        LocalDateTime surrender = LocalDateTime.of(1945, 8, 15, 0, 0);
        if (independence.isBefore(koreanWar))
            System.out.println("광복 이후 한국전쟁이 발발하였다. ");
        if (koreanWar.isAfter(independence))
            System.out.println("한국전쟁은 광복 이후에 발발하였다.");
        if (independence.isEqual(surrender))
            System.out.println("일왕의 항복과 대한독립은 같은 날에 일어났다.");
        if (independence.compareTo(surrender) == 0)
            System.out.println("일왕의 항복과 대한독립은 같은 날에 일어났다.");
    }
}
