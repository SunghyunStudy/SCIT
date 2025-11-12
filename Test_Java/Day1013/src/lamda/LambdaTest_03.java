package lamda;

/* 인터페이스에 들어갈 수 있는 놈들  :   FunctionalInterface에 들어갈 수 있는 숫자
    상수변수         :     여러개 가능
    메소드 선언부만  :     1개만 가능
    static 메소드    :     여러개 가능
    default 메소드   :     여러개 가능
 */

 // @FunctionalInterface  :  람다용 인터페이스라는 뜻임 = implements 못함. and  abstract 메서드를 1개만 가질 수 있음. plus 메서드 외에는 안됨.
@FunctionalInterface
interface SimpleCalc3 {
    int plus(int a, int b);
}



public class LambdaTest_03 {
    public static void main(String[] args) {
        SimpleCalc3 calc = (int a, int b) -> a + b;   // main에서 생성한 calc를 람다로 표현해서 람다 객체를 선언한거임.  LambdaTest_02의 긴 코드와 같은 역할
        int result = calc.plus(12, 34);


        // 메서드를 이용하는 방법
        int result2 = myFunction( (a, b) -> a + b );

        // Myinterface를 부르는 방법
        int result3 = myFunction2( (a, b) -> a + b);

    }

    public static int myFunction(SimpleCalc3 c){
        return c.plus(1, 2);
    }

    public static int myFunction2(MyInterface c2){
        return c2.calculate(1, 2);
    }
}
