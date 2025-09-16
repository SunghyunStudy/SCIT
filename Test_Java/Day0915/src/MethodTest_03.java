/*
[문제] 정수값을 전달받아 소수인지 아닌지 판단하는 메소드를 작성하시오

// 전달받은 data가 소수이면 true, 아니면 false를 반환하는 메소드를 작성하시오
public static boolean isPrime(int data);

 */
import java.sql.SQLOutput;
import java.util.Scanner;

public class MethodTest_03 {
    public static void main(String[] args) {
        int a = 12;
        MethodTest_03 mt = new MethodTest_03();
        boolean result = mt.isPrime(a);

        if(result) System.out.printf("%d는 소수입니다.", a);
        else System.out.printf("%d는 소수가 아닙니다.", a);

    }

    // non static 이면 호출 불가 (new를 사용해서 불러와야됨.)
    public boolean isPrime(int data){  // static은 실행시키면 메모리에 올라가지만 static이 없다면 생성해야 메모리에 올라감.
        int count = 0;
        for (int i = 2; i < data; ++i){
            if(data % i == 0){
                return false;
            }
        }
        return true;
    }
}

