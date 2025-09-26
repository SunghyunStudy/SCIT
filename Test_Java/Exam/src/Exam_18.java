import java.util.Scanner;

/*
[문제-18] 특정 년도 구하기
연도를 입력 받아 서기 1년~입력 년도 n사이의 모든 돼지의 해를 출력하고 돼지의 해가 몇 번인지 출
력하시오

1) Hint
돼지의 해는 연도를 12로 나누어 나머지가 3인 해이다.

2) 실행 결과
년도 입력 : 2019
1 년 2019 년까지 돼지의 해 :
3 년
15 년
27 년
39 년
51 년
...
2007 년
2019 년
1 년에서 2019 년까지 돼지의 해는 169 번 있습니다
 */
public class Exam_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int birth, count = 0;

        System.out.print("태어난 년도 입력 : ");
        birth = sc.nextInt();

        for(int i = 0; i <= birth; i++){
            if(i % 12 == 3){
                System.out.println(i + "년");
                count++;
            }
        }
        System.out.printf("1 년에서 %d 년까지 돼지의 해는 %d 번 있습니다.", birth, count);
    }
}
