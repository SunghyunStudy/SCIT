/*
[문제] 두 개의 숫자( 정수를 입력받는다.)

더하기, 빼기, 곱하기를 실행하는 메소드를 작성하시오.
public static int plus(int a, int b);
public static int minus(int a, int b);
public static int multiply(int a, int b);
 */

import java.util.Scanner;

public class MethodTest_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result=0;
        String op, strResult="";


        System.out.print("a를 입력: ");
        int a = sc.nextInt();
        System.out.print("b를 입력: ");
        int b = sc.nextInt();

        System.out.println("어떤 연산을 할건지?(+, -, *) : ");
        op = sc.next();
        switch(op){
            case "+" :
                result = plus(a,b);
                break;
            case "-":
                result = minus(a,b);
                break;
            case "*":
                result = multiply(a,b);
                break;
            default:
                System.out.println("연산자를 잘못 입력했습니다.");

//              강제종료하는 법 2가지
                // 1) System.exit
//              System.exit(1); // 매개변수가 상태값임.
                // System.exit 메서드는 응용 프로그램이 exit() 메서드 아래에 있는 코드를 실행 하지 않음. = 강제종료

                // 2) return;
                // main 메서드는 void 이기 때문에 return; 을 하면 아무것도 반환하지 않고 프로그램을 종료하게 됨.
                return;
        }
        System.out.printf("%d %s %d : %d", a, op, b, result);
    }

    public static int plus(int a, int b){
        return a + b;
    }

    public static int minus(int a, int b){
        return a - b;
    }

    public static int multiply(int a, int b){
        return a * b;
    }
}
