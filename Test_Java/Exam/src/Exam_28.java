/*
[문제-28] 2차원 배열을 이용한 채점 프로그램의 작성

배열을 이용한 사지선다형 객관식 시험채점 프로그램을 작성하시오.
(이때 정답은 1, 2, 3, 4 중의 하나이다.)

1) 요구사항
1)  문제 수만큼 정답을 입력받아 배열에 저장한다.
2) 시험을 치른 학생의 답을 입력받는다.
3) 정답과 학생의 답을 맞추어 결과와 맞은 개수를 출력한다.

2) Hint
final int cnt = 10;
int[][] answer = new int[2][cnt];
char[] result = new char[cnt];
int rightCnt = 0;
// 문제 수
// 0 행에는 정답을 입력 받고, 1행에는 학생의 답을 입력 받는다.
// o x 가 입력될 배열
// 맞춘 개수

3) 실행 결과
정답을 입력하세요 : 1 2 4 3 1 3 2 1 4 3
학생의 답을 입력하세요 : 1 3 2 3 1 3 1 4 3 3
결과: o x x o o o x x x o (맞은 개수 : 5개)
 */

import java.util.Scanner;

public class Exam_28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int cnt = 10;
        int[][] answer = new int[2][cnt];
        char[] result = new char[cnt];
        int rightCnt = 0;

        System.out.println("정답을 입력하세요 : ");
        for(int i = 0; i < cnt; i++){
            answer[0][i] = sc.nextInt();
        }

        System.out.println("학생의 답을 입력하세요 : ");
        for(int i = 0; i < cnt; i++) {
            answer[1][i] = sc.nextInt();
        }

        for (int i = 0; i < cnt; i++){
            if(answer[0][i] == answer[1][i]){
                result[i] = 'o';
                rightCnt++;
            }else result[i] ='x';
        }

        System.out.print("결과 : ");
        for (int i = 0; i < cnt; i++){
            System.out.print(result[i] + " ");
        }

        System.out.printf(" (맞은 개수 : %d 개)", rightCnt);
    }
}
