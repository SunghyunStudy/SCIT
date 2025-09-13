/*
[문제-5] 단위 바꾸기(cm  inch)
단위를 입력 받고 길이를 입력 받아 cm는 inch로, inch는 cm로 변환하는 코드를 작성하시오.
1) Hint
1 inch는 2.54 cm
1cm는 0.3937 inch

2) 실행 예
입력단위는 (1. cm, 2. Inch) : 1
길이 입력 : 34
34 cm = 13.385826771653543 inch
 */


import java.util.Scanner;

public class Exam_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length, measurement;
        double trans;

        System.out.print("입력단위는 (1. cm, 2. Inch) : ");
        measurement = sc.nextInt();
        System.out.print("길이 입력 : ");
        length = sc.nextInt();


        if(measurement == 1){
            trans = length * 0.3937;
            System.out.printf("%d cm = %f inch", length, trans);
        } else if(measurement ==2){
            trans = length * 2.54;
            System.out.printf("%d inch = %f cm", length, trans);
        } else{
            System.out.println("잘못 선택했습니다.");
        }

    }
}
