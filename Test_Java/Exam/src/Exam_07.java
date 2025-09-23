
import java.util.Scanner;

public class Exam_07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hours;
        int basic_salary = 10320;
        double net = 0;
        double tax = 0;
        double gross = 0;

        System.out.print("1주일 간의 근무시간을 입력하시오 : ");
        hours = sc.nextInt();

        // 총수입 계산
        if (hours <= 40) {
            gross = hours * basic_salary; // 선언하지 않고 값만 변경
        } else {
            gross = (40 * basic_salary) + ((hours - 40) * basic_salary * 1.5);
        }

        // 세금계산
        if (gross < 300000){
            tax = gross * 0.15;
        }else if (gross >= 300000){
            tax = (300000 * 0.15 + (gross - 300000) * 0.25);
        }

        // 실수령액
        net = gross - tax;

        System.out.printf("총 수입 : %,.0f원%n", gross);
        System.out.printf("세금 : %,.0f원%n", tax);
        System.out.printf("실수입 : %,.0f원%n", net);
    }
}