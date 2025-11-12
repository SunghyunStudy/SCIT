package wrapper;

/*
    주어진 문자열을 분해하여 한명의 학생의 합계와 평균을 구하시오.
 */

public class WrapperTest_02 {
    public static void main(String[] args) {
        String score = "홍길동 100 75 85";
        String[] scoreList = score.split(" ");
        int sum = 0;
        double avg;
        String name = "";

        for(int i = 0; i < scoreList.length; i++){
            if(i == 0){
                name = scoreList[i];
                continue;
            }
            sum += Integer.parseInt(scoreList[i]);
        }

        avg = sum / 3.0;        // 3.0이 킥임. total은 int고 3도 int이기 때문에 하나는 소수여야함.
        System.out.printf("이름 : %s,  합계 : %d, 평균 : %.2f%n", name, sum, avg);

        // int : 4byte -> 32bit : -2^31 ~ +2^31 - 1
        System.out.println("int형의 바이트 : " + Integer.BYTES);         // Integer는 클래스 .뒤에 값이 대문자면 상수값인거임
        System.out.println("int형의 bit수  : " + Integer.SIZE);
        System.out.println("int형의 최댓값 : " + Integer.MAX_VALUE); // 7fff => 0111 1111 1111 1111 ~~ :
        System.out.println("int형의 최솟값 : " + Integer.MIN_VALUE);
        System.out.println("비트의 1갯수 : " + Integer.bitCount(12));    // bitcount : 1의 갯수 //    12   ==>  1100

        String str = "12";
        int i1 = Integer.valueOf(str);

        int i2 = Integer.parseInt("A", 16); // "A" 를 16진수로 변경
        int i3 = Integer.parseInt(str, 16); // 12를 16진수로 변경  // 16진수 "12"를 10진수로 계산하면 (1 * 16¹) + (2 * 16⁰)이 됩니다.
        System.out.println(i2);
        System.out.println(i3);


    }
}
