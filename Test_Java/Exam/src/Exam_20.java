import java.util.Scanner;

public class Exam_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input, sum = 0;
        System.out.print("정수 입력 : "); input = sc.nextInt();

        for(int i = 0; i <= input; i++){
            sum += i;
        }
        System.out.printf("1~%d까지 더한 값 : %d", input, sum);
    }
}
