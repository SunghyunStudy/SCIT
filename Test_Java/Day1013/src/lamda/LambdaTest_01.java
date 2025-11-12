package lamda;

interface MyInterface {
    public int calculate(int a, int b);
}


public class LambdaTest_01 {
    public static void main(String[] args) {
        MyInterface f1 = new MyInterface() {  // Myinterface에서는 구현이 되어있지않기 때문에 객체 생성이 불가능함 하지만 생성하는 동시에 내부에 오버라이딩을 하면 가능.
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        int result = f1.calculate(15, 20);  // 이것도 수정못함. 지역변수이기 때문에
        System.out.println(result);
    }
}