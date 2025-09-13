// 변수의 접근범위 : Scope

public class VariableTest_01 {
    int myData; // 인스턴스 변수

    public static void main(String[] args) {
        int a = 100;   // 멤버 변수

        // 블럭 1번
        {
          //int a = 1;   블럭 밖에서 같은 이름의 변수가 선언 되어있기 때문에 오류 발생.
            int b = 2;  //지역 변수
            System.out.println("블럭 1) a = " + a);
            System.out.println("블럭 1) b = " + b);
        } // b 사라짐


        // 블럭 2번
        {
            int b = 22;     // 지역변수끼리는 서로 같은 이름의 변수가 선언되어있어도 오류가 발생하지 않음.
            System.out.println("블럭 2) a = " + a);
            System.out.println("블럭 2) b = " + b);

        } // b 사라짐

        System.out.println("블럭 밖) a = " + a);
        // System.out.println("블럭 밖) b = " + b);


        VariableTest_01 obj;
        obj = new VariableTest_01();


        VariableTest_01 obj2 = new VariableTest_01();
        // myData는 참조변수의 참조값을 통해서만 사용가능
        System.out.println("인스턴스 변수 사용 : " + obj2.myData);
        System.out.println("참조변수 obj2값 :" + obj2);
    }
}
