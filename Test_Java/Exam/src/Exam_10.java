import java.util.Scanner;

public class Exam_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mari, minutes, exchangeMinute;



        System.out.print("박테리아가 몇 마리입니까? ");
        mari = sc.nextInt();
        System.out.print("경과시간을 입력 해주세여. (분) ");
        minutes = sc.nextInt();

        mari += 10 * (minutes / 30);

        System.out.printf("총 박테리아 수 : %d%n ", mari);

    }
}
