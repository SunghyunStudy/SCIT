package wrapper;

/*
    Wrapper :  기본자료형 8개를 객체형으로 만든 클래스
               int -> Integer,  char -> Character,  double -> Double
               void -> Void,
 */


public class WrapperTest_01 {
    public static void main(String[] args) {
//        Integer i1 = new Integer("12");     // 예전에 쓰던 코드임 지금 못 씀 아래가 요즘 버전
        Integer i2 = Integer.valueOf(12);  // Integer 타입으로 바꿈.
        String value = "123";
        Integer i3 = Integer.valueOf(value);
        Integer i4 = Integer.parseInt(value);
        Integer i5 = Integer.valueOf(value);
        Integer i7 = Integer.valueOf("12");

        Integer i6 = i3 + i4;   // Boxing, Unboxing // 객체는 덧셈을 못하는데 unboxing이 된 뒤에 연산을 하고 다시 Boxing이 된거임.
        System.out.println("연산 결과 : " + i6);    // 연산 결과 : 246
        System.out.println(i3 == i4);               // true  :  왜 true지? -> hash(다른 곳에 있어도 값이 같다면 같은 곳에 있는것 처럼 만들어진 주소) 값이 같기 때문
        System.out.println(i3 == i5);               // true
        System.out.println(i3 == i7);               // false : hash주소가 다름 = 값이 다름

    }
}
