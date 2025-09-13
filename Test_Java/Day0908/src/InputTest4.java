import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class InputTest4 {
    public static void main(String[] args) throws IOException {
        // InputStream keyin = System.in;

        // 반드시 오류처리를 해야함
        // 메시지를 출력해주는 것이 좋음 멈춤
        // 키보드로 입력받는 것은 숫자가 없다.
        // --> 아스키코드로만 입력받는다!! ㅠㅠ
        // 1개밖에 입력받지 못한다.
//        System.out.print("값을 입력하시오: ");
//        int data = keyin.read();
//
//        System.out.println("니가 입력한 값은: " + data);

        // 1) Scanner 선언
        Scanner keyin = new Scanner(System.in);

        // 2) 메시지 출력
        System.out.print("나이를 입력: ");

        // 3) 입력받아 저장하는 변수
        int age = keyin.nextInt();

        // 4) 출력
        System.out.printf("너의 나이는 %d살이구나!", age);



    }
}
