/*
    [문제]
    이름, 성별, 키, 몸무게를 입력받고,
    적정체중과 BMI 계산한 후
    BMI 결과에 따라 비만, 과체중, 정상, 저체중으로 나누어 결과를 출력하시오

    if 3버전으로 출력하시오

    출력결과
    입력받은 모든 값과 계산되서 나온 모든 결과 + BMI결과 출력
 */

import java.util.Scanner;

public class IfTest_03 {
    public static void main(String[] args) {
        Scanner keyin = new Scanner(System.in);
        int height, weight;
        String name, gender, bmiResult;
        double stdWeight, bmi;

        System.out.print("이름:");
        name = keyin.next();
        System.out.print("성별:");
        gender = keyin.next();
        System.out.print("키:");
        height = keyin.nextInt();
        System.out.print("몸무게:");
        weight = keyin.nextInt();

        stdWeight = (height - 100) * 0.9;

        // bmi 구하기
        bmi = weight / ((height*0.01) * (height*0.01)) ;

        if(bmi >= 30) bmiResult = "비만";
        else if(bmi >= 25 && bmi < 30) bmiResult = "과체중";
        else if(bmi >= 18.5 && bmi < 25) bmiResult = "정상체중";
        else bmiResult = "저체중";

        System.out.printf("이름: %s, 성별: %s, 키: %d, 몸무게: %d, 적정체중: %.2f, bmi: %.2f / %s입니다.",
                name, gender, height, weight, stdWeight, bmi,  bmiResult);

    }
}
