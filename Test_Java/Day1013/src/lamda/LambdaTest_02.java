package lamda;

/* 인터페이스에 들어갈 수 있는 놈들
    상수변수
    메소드 선언부만
    static 메소드
    default 메소드
 */


interface SimpleCalc {
    int value = 15;  // 상수변수는 변수 이름이 누워 있음.

    public int plus(int a, int b);
    public int minus (int a, int b);

    public default double multifly(int a, int b){ // 인터페이스에서 구현은 금지인데 static과 default는 구현 가능함.
        return (double) a * b;
    }
}

public class LambdaTest_02 {
    public static void main(String[] args) {
        SimpleCalc calc = new SimpleCalc() {
            @Override
            public int plus(int a, int b) {
                return a + b;
            }

            @Override
            public int minus(int a, int b) {
                return a - b;
            }
        };

    }
}
