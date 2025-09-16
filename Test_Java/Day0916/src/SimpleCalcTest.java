public class SimpleCalcTest {
    public static void main(String[] args) {
        SimpleCalc sc = new SimpleCalc();

        int a;

        a = sc.plus(12,23);     // 메소드 호출
        System.out.println("plus 결과: " + a);

        a = sc.minus(10,7);     // 메소드 호출
        System.out.println("minus 결과: " + a);

        a = sc.multiply(2,7);     // 메소드 호출
        System.out.println("multiply 결과: " + a);
    }
}
