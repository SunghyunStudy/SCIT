/*
[문제-25] 입장권 구매
어떤 놀이동산에서 관람객의 입장료를 징수하는 시스템을 개발하려고 한다.
만 18~60세는 일반비용으로 징수하고 18세미만, 60세 초과 인원은 15% 할인가격을 적용하는 프로그램
이다. 아래 제시된 실행 결과를 참고하여 프로그램을 완성하시오.

1) 주의
• continue와 break 명령문을 사용한다.
• 성인 1인 기본요금: 43,000
• 고객은 반복적으로 입력 받되, 총 인원수 입력 시 0명이 입력되면 처음부터 다시 입력받는다.
• 종료 조건: 입력 받은 총 인원수가 할인적용 인원수보다 적을 경우 시스템 종료한다.

2) 실행 결과
다음 손님 입장해 주세요.
* 총 몇 명? 0

다음 손님 입장해 주세요.
* 총 몇 명? 5
* 미성년 인원수는? 2
* 노인 인원수는? 1

* 1인 기본요금: 43,000원
* 미성년 할인요금: 12,900원
* 노인 우대요금: 6,450원
총 지불금액은 195,650원입니다.

다음 손님 입장해 주세요.
* 총 몇 명? 3
* 미성년 인원수는? 2
* 노인 인원수는? 2
시스템 종료합니다.
 */

import java.util.Scanner;

public class Exam_25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rate = 43000;
        double discount = 0.15;
        int totalNum, juniorNum, seniorNum;

        while(true){
            System.out.println("다음 손님 입장해 주세요");
            System.out.print("* 총 몇 명?");
            totalNum = sc.nextInt();
            if(totalNum == 0) continue;

            System.out.println("* 미성년 인원수는?");
            juniorNum = sc.nextInt();

            System.out.println("* 노인 인원수는?");
            seniorNum = sc.nextInt();

            if(totalNum < juniorNum + seniorNum) break;


            double total_rate =  ((totalNum - juniorNum - seniorNum) * rate) + juniorNum * rate * 0.85 + seniorNum * rate * 0.85;

            System.out.printf("1인 기본요금: %,d%%n", rate);
            System.out.printf("미성년 할인요금: %,.0f%n", (rate*discount) * juniorNum);
            System.out.printf("노인 할인요금: %,.0f%n", (rate*discount) * seniorNum);
            System.out.printf("총 지불금액은 %,.0f 원입니다.%n", total_rate );

        }

    }
}
