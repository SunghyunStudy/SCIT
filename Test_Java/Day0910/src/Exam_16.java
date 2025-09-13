import java.util.Scanner;

/*
[문제-16] while문을 이용하여 달걀 포장 프로그램의 작성
달걀의 무게를 입력 받아 달걀을 포장하는 프로그램을 작성하라. 달걀의 무게를 입력 받아 무게가
150g 이상이면 포장하고, 150g 미만인 것은 포장에서 제외하여 총 10개의 달걀을 한 박스 포장하는 프
로그램을 작성하라. 프로그램 작성 시 무한 반복문과 break와 continue문을 활용하여 작성한다.

1) 실행 결과
# 달걀무게를 입력하시오(g단위) : 160(엔터)
* 달걀개수 : 1개
# 달걀무게를 입력하시오(g단위) : 100(엔터)
* 메추리알 가지고 장난하지 마시오~ ^^
# 달걀무게를 입력하시오(g단위) : 200(엔터)
* 달걀개수 : 2개
# 달걀무게를 입력하시오(g단위) : 170(엔터)
* 달걀개수 : 3개
# 달걀무게를 입력하시오(g단위) : 85(엔터)
* 메추리알 가지고 장난하지 마시오~ ^^
:
:
# 달걀무게를 입력하시오(g단위) : 190(엔터)
* 달걀개수 : 10개
*** 달걀포장이 끝났습니다.
 */
public class Exam_16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int egg_num = 0;
        int weight;

        while(egg_num != 10){
            System.out.print("달걀무게를 입력하시오(g단위) : ");
            weight = sc.nextInt();

            if (weight < 150){
                System.out.printf("달걀 개수 : %d개%n", egg_num);
                continue;
            }else {
                ++egg_num;
                System.out.printf("달걀 개수 : %d개%n", egg_num);
            }
        }
    }
}
