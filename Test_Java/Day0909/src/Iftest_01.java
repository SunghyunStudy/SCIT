public class Iftest_01 {
    public static void main(String[] args) {
        int data = 5;

        // 첫번째 버전
        if(data > 10) System.out.println("10이 넘습니다.");

        // 두번째 버전: if ~ else
        if(data % 2 == 0) System.out.println("짝수입니다.");
        else System.out.println("홀수입니다.");

        // 세번째 버전 : if ~ else if ~ else if ... ~ else
        if(data % 3 == 0) System.out.println("3의 배수");
        else if(data % 3 == 1) System.out.println("나머지가 1인 수");
        else if(data % 3 == 2) System.out.println("나머지가 2인 수");


        System.out.println("끝.");
    }
}
