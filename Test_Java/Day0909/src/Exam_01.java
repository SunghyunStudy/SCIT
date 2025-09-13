/*
    [문제] 키보드로 데이터 (정수)를 입력받아서 양수인지 아닌지 출력

    <실행결과>
    수: 35
    35는 양수입니다.

    수: -1
    -1는 양수가 아닙니다.
 */
/*
import java.util.Scanner;

public class Exam_01 {
    public static void main(String[] args) {
        // 클래스 타입
        Scanner sc = new Scanner(System.in);
        System.out.println("정수를 입력: ");

        // nextInt(), nextDouble(), next() : 문자열
        int i = sc.nextInt();

        if (i >= 0) {
            System.out.printf("%d는 양수입니다.", i);
        } else System.out.printf("%d는 음수입니다.", i);
    }
}
*/
// 교수님 답
import java.util.Scanner;

public class Exam_01 {
    public static void main(String[] args) {
        // 클래스 타입
        Scanner scanner = new Scanner(System.in);
        int value;
        String result;

        // 입력
        System.out.println("숫자를 입력: ");
        value = scanner.nextInt();

        // 처리
        result = (value>0) ? "는 양수입니다." : (value<0) ? "는 음수입니다." : "입니다.";

        // 출력
        System.out.printf("%d%s", value, result);
    }
}