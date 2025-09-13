public class VariableTest_02 {
    static int staticVariable = 25;
    static final int MY_DATA = 55;      //상수 변수
    int myData = 10;
    public static void main(String[] args) {
        //인스턴스 변수라면 VariableTest_02 obj = new ~~~ 이렇게 생성해야됨.
        System.out.println("클래스 변수 값: " + VariableTest_02.staticVariable);

        VariableTest_02 obj = new VariableTest_02();
        System.out.println("인스턴스 변수 값 : " + obj.myData);

        System.out.println("상수 변수의 값 : " + VariableTest_02.MY_DATA);

        // Math.PI;   // PI는 Math 클래스 안에 static final로 선언되어있을거임. (대문자 + 눕혀진 글자)
    }
}
