public class MethodTest_01 {
    public static void main(String[] args) {
        int result = add(10, 5);  // 11이 되돌아옴
        System.out.println("메소드 실행결과: " + result);

        System.out.println("메소드 실행결과: " + add(12, 34));

        output(1, 15);
    }

    // 덧셈 메서드
    public static int add(int a, int b){
        int result = a + b;
        output(a, b);
        return result;
    }

    // a~b 출력하는 메서드
    public static void output(int a, int b) { // a, b는 지역변수라서 다른 탭의 변수와 이름이 같아도 상관없음.
        if(a < b){
            for(int i = a; i <= b; ++i) System.out.print(i + " ");
            System.out.println();
        }
    }
}
