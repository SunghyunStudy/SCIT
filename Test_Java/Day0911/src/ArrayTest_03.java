import java.util.Scanner;

public class ArrayTest_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] sary = new String[5];

        // 입력 for문
        for(int i = 0; i < sary.length; ++i){
            System.out.print("오늘 먹고싶은거 입력 : ");
            sary[i] = sc.next();
        }
        System.out.println();

        // 출력 for 문
        for (int j = 0; j < sary.length; ++j){
            System.out.println("== 먹고 싶은 목록 ==");
            System.out.println(sary[j]);
        }
    }
}
