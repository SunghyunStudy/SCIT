/*
    call by value : 다른 메소드에서 원본 데이터에 접근 불가능
 */

public class MethodCall_01 {
    public static void main(String[] args) {
        int a = 10, b = 12;  // 지역변수
        System.out.println("a = " + a + ", b = " + b);

        exchange(a, b);
        System.out.println("a = " + a + ", b = " + b);
    }

    public static void exchange(int a, int b) {       // main 메서드가 static이라서 이것도 static으로 선언.. -> 호출하는 쪽이 static이면 무조건 static으로 선언되어야 함.
        int temp;

        temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a + ", b = " + b);      // 8개의 기본 자료형을 갖고 데이터를 넘기는 걸 call by value라고 함(= exchange(a, b);)
    }
}