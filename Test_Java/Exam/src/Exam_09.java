import java.util.Scanner;

/*
[문제-9] 세 수의 정렬
세 가지 수를 입력 받아 세 가지 수 중에 가장 큰 수부터 내림차순으로 정렬 하시오.

1) 실행 결과
1번째 수 입력 : 56
2번째 수 입력 : 45
3번째 수 입력 : 89
89 56 45
 */
public class Exam_09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c, temp;

        System.out.print("1번째 수 입력: ");
        a = sc.nextInt();
        System.out.print("2번째 수 입력: ");
        b = sc.nextInt();
        System.out.print("3번째 수 입력: ");
        c = sc.nextInt();

        if(a < b){
            temp = b;
            b = a;
            a = temp;
        }
        if(a < c){
            temp = c;
            c = a;
            a = temp;
        }
        if(b < c){
            temp = b;
            b = c;
            c = temp;
        }
        System.out.printf("%d  %d  %d", a, b, c);

    }
}
