public class ExceptionTest_04 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int result = 0;

        // a값이 b값보다 작으면 강제로 exception을 터뜨림.
        try{
            if (a < b)
                throw new Exception("a값은 b값보다 커야합니다.");
        }
        catch (Exception e) {
            System.out.println("에러가 발생됨");
//            return;  // 이거 코드 주석 풀었다가 넣었다가 해서 결과문의 차이를 보자
            // return이 있으면 "결과:를 출력하지 않는다. 하지만 finally는 무조건 들러서 정리함
            System.exit(1); // 즉각적으로 프로그램을 종료시키는 코드 (return과 유사) 얘는 finally도 실행안함
        }
        finally {
            System.out.println("리소스 정리하는 코드");
        }

        result = a / b;
        System.out.println("결과 : " + result);
    }
}
