/*
    키보드로 세과목의 점수를 입력받는다.
    합계(int)와 평균(double)을 연산을 한다.
    90점 이상이면 "A학점"
    80점 이상이면 "B학점"
    70점 이상이면 "C학점"
    60점 이상이면 "D학점"
    이하 F
 */

import java.util.Scanner;

public class IfTest_02 {
    public static void main(String[] args) {
        Scanner keyin = new Scanner(System.in);
        int ko, math, eng, total;
        String result;
        double avg;

        System.out.print("국어점수: ");
        ko = keyin.nextInt();
        System.out.print("수학점수: ");
        math = keyin.nextInt();
        System.out.print("영어점수: ");
        eng = keyin.nextInt();

        total = ko + math + eng;
        avg = total / 3.0;

        if(100 >= avg && avg >= 90) result = "A학점";
        else if(avg >= 80) result = "B학점";
        else if(avg >= 70) result = "C학점";
        else if(avg >= 60) result = "D학점";
        else result = "F학점";

        System.out.printf(" %d, %d, %d 합계 : %d, 평균: %.2f, 결과 : %s%n", ko, math, eng, total, avg, result);

    }
}
