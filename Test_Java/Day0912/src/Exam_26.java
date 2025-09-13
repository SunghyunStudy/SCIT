import java.util.Scanner;

/*
[문제-26] 이중 반복문을 이용하여 소수 출력하기
정수를 입력받아 해당 정수까지 있는 모든 소수를 모두 출력하고 소수의 개수를 출력하시오

1) 주의
• 이중 for문 사용할 것
• 숫자 2도 소수이므로 2를 입력했을 때에도 처리해야 한다.

2) 실행 결과
정수 입력: 2
2
2~2 까지 소수의 개수는 1개입니다

정수 입력: 11
2 3 5 7 11
2~11 까지 소수의 개수는 5개입니다

 */
public class Exam_26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inum, divideCount = 0 ,sosuNum = 0;

        while(true){
            System.out.print("정수 입력:");
            inum = sc.nextInt();
            for(int i = 2; i <= inum; i++){
                for(int j = 2; j <= i; j++){
                    if(i % j == 0){
                        divideCount++;
                    }
                }
                if(divideCount == 1){
                    System.out.printf("%d   ", i);
                    sosuNum++;
                }
                divideCount = 0;
            }
            System.out.println();
            System.out.printf("2~%d 까지 소수의 개수는 %d개 입니다.%n", inum, sosuNum);
            sosuNum = 0;
        }
    }
}

/* 소수면 isPrime에 true값을 주고 true인 경우 출력, 소수가 아니면 false를 준 뒤 break함.



 */