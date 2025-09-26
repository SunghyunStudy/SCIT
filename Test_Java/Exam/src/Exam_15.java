import java.util.Scanner;

public class Exam_15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tmp, count = 0 ;

        System.out.println("정수를 입력 : ");
        tmp = sc.nextInt();
        for(int i = 1; i <= tmp; ++i){
            if(tmp % i == 0){
                System.out.println(i);
                count++;
            }
        }

    }
}
