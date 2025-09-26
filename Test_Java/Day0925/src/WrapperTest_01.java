import java.util.Optional;

public class WrapperTest_01 {
    public static void main(String[] args) {
        Integer it = 25;  // boxing
        int result = it + 40 ; // 더할때 unboxing이 일어남 // 대입할땐 그냥 boxing 없이 일반 대입


//        void a = null;

        int x = 0;
        Integer y = null; // 아무것도 참조하지 않고 있는 상태 : null
        y.compareTo(45);

        // null 포인터 에러를 방지
        // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.


    }
}
