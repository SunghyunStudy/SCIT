import java.util.Scanner;

public class Exam_03 {
    public static void main(String[] args) {
        // 변수 선언
        // 키, 적정체중
        // 키를 입력받아서 적정체중을 구한후 출력
        // 키 xxx.xxcm의 적정체중은 xx.xxkg 이다!
        Scanner keyin = new Scanner(System.in);
        double height, stdWeight;

        System.out.print("키를 입력하시오: ");
        height = keyin.nextDouble();

        stdWeight = (height - 100) * 0.9;

        System.out.printf("키 %.2fcm의 적정체중은 %.2fkg입니다.%n", height, stdWeight );
    }
}
