/*
    태어난 해를 4자리로 입력받는다.
    태어난 해를 이용해서 띠를 출력하시오 (12개)
    원숭이, 닭, 개, 돼지, 쥐, 소, 호랑이, 토끼, 용, 뱀, 말, 양
    12로 나눈 나머지가 0이면 원숭이

 */

import java.util.Scanner;

public class SwitchTest_03 {
    public static void main(String[] args) {







        Scanner sc = new Scanner(System.in);
        int birth_year;
        String zodiac = null;

        // 타입별 변수 초기화
        // 정수형 : 0  / 실수형 0.0  /  boolean false  /  문자열 : null

        System.out.print("태어난 해를 입력: ");
        birth_year = sc.nextInt();

/*
        switch(birth_year % 12){
            case 0: zodiac = "원숭이"; break;
            case 1: zodiac = "닭"; break;
            case 2: zodiac = "개"; break;
            case 3: zodiac = "돼지"; break;
            case 4: zodiac = "쥐"; break;
            case 5: zodiac = "소"; break;
            case 6: zodiac = "호랑이"; break;
            case 7: zodiac = "토끼"; break;
            case 8: zodiac = "용"; break;
            case 9: zodiac = "뱀"; break;
            case 10: zodiac = "말"; break;
            case 11: zodiac = "양"; break;
        }
        */

        // Switch 식 방식!
        // default가 무조건 있어야 됨!
        zodiac = switch(birth_year % 12){
            case 0 -> "원숭이";
            case 1 -> "닭";
            case 2 -> "개";
            case 3 -> "돼지";
            case 4 -> "쥐";
            case 5 -> "소";
            case 6 -> "호랑이";
            case 7 -> "토끼";
            case 8 -> "용";
            case 9 -> "뱀";
            case 10 -> "말";
            default -> "양";
        };


        System.out.printf("%d년생은 %s띠입니다.", birth_year, zodiac);
    }
}
