import java.util.Scanner;

public class Exam_21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.print("* 정수를 입력하세요 : "); num = sc.nextInt();
        for(int i = 1; i <= 9; i++){
            if(num > 9 || num < 2) {
                System.out.println("[에러] 2~9사이의 숫자만 입력할 수 있습니다.");
                break;
            }
            System.out.printf("%d x %d = %d%n", num, i, num * i);
        }
    }
}
