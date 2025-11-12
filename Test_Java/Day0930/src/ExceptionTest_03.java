import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;

        for(int i = 0; i < 5; ++i) {
            int result = 0;
            try {
                System.out.print("## 정수 2개 입력 : ");
                a = sc.nextInt();   // 문자열 입력 시 에러 발생하여 catch로 날아감. 이 때 입력 버퍼내의 문자열은 사라지지 않고 남게 됨.
                b = sc.nextInt();
                result = divide(a, b);
            }
//            catch(InputMismatchException e){
//                System.out.println("숫자만 입력하시오");
//                sc.nextLine();
//                continue;
//            }
            catch (Exception e) {
                if(e.getMessage() == null) System.out.println("정수만 입력하세요!");    // 위 방법이 직관적이지만 이렇게도 가능.
                else System.out.println(e.getMessage());
                sc.nextLine();
                continue;
            }
            System.out.println("a / b 나누기 결과 : " + result);
        }
    }

    public static int divide(int a, int b) throws Exception{
        if(b == 0) throw new Exception("두 번째 값은 0을 입력하지 마세요.");  // exception을 발생시켜서 divide()를 호출한 쪽으로 던져준다는 의미
            // return 0;  // 이렇게 반환하면 main에서는 오류가 났음을 인지할 수 없음
        return a / b;
    }
}
