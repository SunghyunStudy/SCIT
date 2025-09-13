import java.util.Scanner;

public class ForTest_01 {
    public static void main(String[] args) {

        for(int i=0; i < 10; i++){ // for() 안에서 선언된 i는 for문에서만 사용 가능.
            System.out.println(i);
        }
        // i는 메모리에서 해제됨


        // 입력받은 단의 구구단을 출력
        Scanner sc = new Scanner(System.in);
        System.out.println("단을 입력 : ");
        int dan = sc.nextInt();

        for(int j = 1; j < 10; j++){
            System.out.printf("%d * %d = %d%n", dan, j, dan * j);
        }
    }
}
