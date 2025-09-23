import java.util.Scanner;

public class Exam_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wage;
        double tax = 0;

        System.out.print("* 연간 근로소득 입력 : ");
        wage = sc.nextInt();

        if (wage <= 2000) tax = wage * 0.05;
        else if (wage > 2000 && wage <= 4000) tax = wage * 0.15;
        else if (wage > 4000 && wage <= 8000) tax = wage * 0.25;
        else if (wage > 8000) tax = wage * 0.40;

        System.out.printf("* 당신의 소득세는 : %.0f 입니다.", tax);
    }
}
